package com.example.crudspringbootdynamodb.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.example.crudspringbootdynamodb.dao")
public class DynamoDBConfig {
	
	@Value("${amazon.dynamodb.endpoint}")
	private String dynamoDBEndPoint;
	
	@Value("${amazon.aws.accesskey}")
	private String accessKey;
	
	@Value("${amazon.aws.secretkey}")
	private String secretKey;
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(new BasicAWSCredentials(accessKey, secretKey));
		if (!StringUtils.isEmpty(amazonDynamoDB)){
			amazonDynamoDB.setEndpoint(dynamoDBEndPoint);
		}
		return amazonDynamoDB;
	}

}
