package com.saldivar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan(basePackages = "com.saldivar")
public class SpringConfig {

	@Bean
	public MongoDbFactory mongoDBFactory() throws Exception {
		return new SimpleMongoDbFactory(new MongoClient("localhost"), "appName");
	}

	@Bean
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDBFactory());
	}

}
