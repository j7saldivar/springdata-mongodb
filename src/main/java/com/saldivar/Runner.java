package com.saldivar;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
		mongoOperation.findAll(User.class).forEach(i -> System.out.println("Mongo operations: " + i));

		cleanup();
		user = new User(username, password);
		userRepository.save(user);
		userRepository.findAll().forEach(i -> System.out.println("Mongo repository: " + i));

		cleanup();
		user = new User(username.toUpperCase(), password);
		userRepository.save(user);
		user = new User(username.toLowerCase(), password);
		userRepository.save(user);
		userRepository.findByUsernameIgnoreCase("George")
				.forEach(i -> System.out.println("Mongo repository query DSL - ignore case: " + i));

		cleanup();
		for (int i = 0; i < 10; i++) {
			user = new User(username, password);
			userRepository.save(user);
		}

		userRepository.findTop5ByUsername("George")
				.forEach(i -> System.out.println("Mongo repository query DSL - top 5: " + i));

		cleanup();
		for (int i = 0; i < 25; i++) {
			user = new User(username, password);
			user.setAge(new Random().nextInt(10));
			userRepository.save(user);
		}

		userRepository.findByAgeGreaterThanEqual(5)
				.forEach(i -> System.out.println("Mongo repository query DSL - greater than or equal: " + i));

		cleanup();
		user = new User(username, password);
		user.setDob(new Date());
		userRepository.save(user);
		user = new User(username, password);

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -10);
		calendar.getTime();
		user.setDob(calendar.getTime());
		userRepository.save(user);

		Calendar before = Calendar.getInstance();
		before.add(Calendar.DATE, -1);
		userRepository.findByDobBetween(before.getTime(), new Date())
				.forEach(i -> System.out.println("Mongo repository query DSL - between: " + i));

		
		System.out.println("Mongo repository query DSL - and: " + userRepository.findFirstByUsernameAndPassword(username, password));
		userRepository.querySomeData();

	}

	public void cleanup() {
		userRepository.deleteAll();
	}

}
