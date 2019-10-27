package com.challenge.configserver.aws.ssm;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import org.springframework.cloud.aws.paramstore.AwsParamStorePropertySource;
import org.springframework.cloud.config.environment.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class CustomAwsParamStorePropertySource extends AwsParamStorePropertySource{

	public CustomAwsParamStorePropertySource(String context, AWSSimpleSystemsManagement ssmClient) {
		super(context, ssmClient);
		init();
	}
	
	public PropertySource getProperties(String name) {

		String[] keys = getPropertyNames();
		Map<Object, String> map = new HashMap<>();

		for (String key : keys) {
			String prop = (String)getProperty(key);
			 map.put(key.replaceFirst(".", ""), prop);
		}
        return new PropertySource(name, map);
    }
}
