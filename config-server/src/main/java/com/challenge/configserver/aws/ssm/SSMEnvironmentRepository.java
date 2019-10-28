package com.challenge.configserver.aws.ssm;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SSMEnvironmentRepository implements EnvironmentRepository{

	    @Autowired
	    private AWSSimpleSystemsManagement ssmClient;
	    private static final String PROFILE_DEFAULT = "default";
		
	    @Override
		public Environment findOne(String application, String profile, String label) {

			Environment env = new Environment(application, profile);
			  
			  if(!PROFILE_DEFAULT.equals(profile)) {
				  String baseContext = String.format("/%s/%s", profile, application);
			      env.add(this.create(baseContext, application));
			  }
			  
		      return env;
		}
		
		private PropertySource create(String context, String application) {

			CustomAwsParamStorePropertySource propertySource = new CustomAwsParamStorePropertySource(context,
					this.ssmClient);
			return propertySource.getProperties(application);
		}
}