package com.saldivar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.saldivar.config.SpringConfig;
import com.saldivar.model.User;

public class MainApp {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
		applicationContext.getBean(Runner.class).run();

	}

}
