package com.saldivar.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@PropertySource("application.properties")
@ComponentScan(basePackages = "com.saldivar")
@EnableMongoRepositories(basePackages = "com.saldivar.repository", repositoryImplementationPostfix = "CustomImpl")
public class SpringConfig {

	@Bean
	public MongoDbFactory mongoDBFactory() throws UnknownHostException  {
		return new SimpleMongoDbFactory(new MongoClient("localhost"), "appName");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws UnknownHostException {
		return new MongoTemplate(mongoDBFactory());
	}

}
