package com.saldivar.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
public @Data class User {

	private @Id String id;
	private String username;
	private String password;
	private Date dob;
	private int age;
	private @Version Long version;

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
