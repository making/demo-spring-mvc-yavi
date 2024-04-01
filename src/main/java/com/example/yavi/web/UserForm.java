package com.example.yavi.web;

import am.ik.yavi.arguments.Arguments;
import am.ik.yavi.core.ValueValidator;
import com.example.yavi.domain.User;
import jakarta.annotation.Nullable;

public record UserForm(@Nullable String name, @Nullable String email, @Nullable Integer age) {
	static ValueValidator<UserForm, User> validator = User.validator
		.compose(userForm -> Arguments.of(userForm.name(), userForm.email(), userForm.age()));

}
