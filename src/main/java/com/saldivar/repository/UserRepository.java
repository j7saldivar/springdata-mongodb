package com.saldivar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.saldivar.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

	List<User> findByUsernameIgnoreCase(String username);
	List<User> findTop5ByUsername(String username);
	List<User> findByAgeGreaterThanEqual(int age);
	List<User> findByDobBetween(Date before, Date after);
	User findFirstByUsernameAndPassword(String username, String password);
	
}
