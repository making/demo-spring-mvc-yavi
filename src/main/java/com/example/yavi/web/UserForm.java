package com.example.yavi.web;

import am.ik.yavi.core.ValueValidator;
import com.example.yavi.domain.User;

public class UserForm {
	static ValueValidator<UserForm, User> validator = User.validator
			.compose(userForm -> new User(userForm.getName(), userForm.getEmail(), userForm.getAge()));

	private String name;

	private String email;

	private Integer age;

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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
