package com.example.yavi.domain;

public class User {

    private final String name;

    private final String email;

    private final Integer age;

    User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

	public String name() {
		return this.name;
	}

	public String email() {
		return this.email;
	}

	public Integer age() {
		return this.age;
	}

	@Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", age=" + age +
            '}';
    }
}
