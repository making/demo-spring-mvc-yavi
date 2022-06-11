package com.example.yavi.web;

import am.ik.yavi.core.ValueValidator;
import com.example.yavi.domain.User;

public interface UserForm {
	ValueValidator<UserForm, User> validator = User.validator
			.compose(userForm -> new User(userForm.getName(), userForm.getEmail(), userForm.getAge()));

	String getName();

	String getEmail();

	Integer getAge();
}
