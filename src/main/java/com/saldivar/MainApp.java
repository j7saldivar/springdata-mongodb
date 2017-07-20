package com.saldivar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.saldivar.config.SpringConfig;
import com.saldivar.model.User;

/**
 * Install MongoDB using Ubuntu
 * https://docs.mongodb.com/manual/tutorial/install-mongodb-enterprise-on-ubuntu/
 * 
 * Download compass (Mongodb GUI):
 * https://www.mongodb.com/download-center?filter=enterprise#compass
 * 
 * @author saldivar
 *
 */
public class MainApp {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		applicationContext.getBean(Runner.class).run();

	}

}
