package com.saldivar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.saldivar.model.User;

@Component
public class Runner {

	private MongoOperations mongoOperation;

	@Autowired
	public Runner(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	public void run() {

		User user = new User();
		user.setUsername("Username");
		user.setPassword("pass");
		mongoOperation.save(user);

	}

}
