package com.example.yavi.web;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validator;

public record UserForm(String name, String email, Integer age) {
	static Validator<UserForm> validator = ValidatorBuilder.<UserForm>of()
			.constraint(UserForm::name, "name", c -> c
					.notBlank()
					.lessThanOrEqual(20))
			.constraint(UserForm::email, "email", c -> c
					.notBlank()
					.lessThanOrEqual(50)
					.email())
			.constraint(UserForm::age, "age", c -> c
					.notNull()
					.greaterThanOrEqual(0)
					.lessThanOrEqual(200))
			.build();
}
