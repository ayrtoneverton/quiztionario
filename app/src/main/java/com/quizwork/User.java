package com.quizwork;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class User implements Serializable {
	private long id;
	private String name;
	private String email;
	private String password;

	public User(long id) {
		this.id = id;
	}

	public User(long id, String name, String email) {
		this.id = id;
		this.name = name;
		this.email = email;
	}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(long id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NonNull
	@Override
	public String toString() {
		return name;
	}
}
