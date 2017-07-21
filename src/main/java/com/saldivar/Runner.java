package com.saldivar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.saldivar.model.User;
import com.saldivar.repository.UserRepository;

@Component
public class Runner {

	private MongoOperations mongoOperation;
	private UserRepository userRepository;
	
	@Value("${username}")
	private String username;
	
	@Value("${password}")
	private String password;
	
	@Autowired
	public Runner(MongoOperations mongoOperation, UserRepository userRepository) {
		this.mongoOperation = mongoOperation;
		this.userRepository = userRepository;
	}

	public void run() {

		cleanup();
		User user = new User(username, password);
		mongoOperation.save(user);
		mongoOperation.findAll(User.class).forEach(i -> 
			System.out.println("Mongo operations: " + i)
		);
		
		cleanup();
		user = new User(username, password);
		userRepository.save(user);
		userRepository.findAll().forEach(i -> 
			System.out.println("Mongo repository: " + i)
		);
		
		cleanup();
		user = new User(username.toUpperCase(), password);
		userRepository.save(user);
		user = new User(username.toLowerCase(), password);
		userRepository.save(user);
		userRepository.findByUsernameIgnoreCase("George").forEach(i -> 
			System.out.println("Mongo repository query DSL: " + i)
		);
		
		userRepository.querySomeData();
		
		

	}

	public void cleanup() {
		userRepository.deleteAll();
	}

}
