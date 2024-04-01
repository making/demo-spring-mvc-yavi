package com.example.yavi.web;

import am.ik.yavi.arguments.Arguments;
import am.ik.yavi.core.ValueValidator;
import com.example.yavi.domain.User;

public record UserForm(String name, String email, Integer age) {
	static ValueValidator<UserForm, User> validator = User.validator
			.compose(userForm -> Arguments.of(userForm.name(), userForm.email(), userForm.age()));
}
