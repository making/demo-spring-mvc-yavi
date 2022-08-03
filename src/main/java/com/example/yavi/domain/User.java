package com.example.yavi.domain;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.ApplicativeValidator;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public record User(String name, String email, Integer age) {
	public static final ApplicativeValidator<User> validator = ValidatorBuilder.<User>of()
			.constraint(User::name, "name", c -> c
					.notBlank()
					.emoji()
					.lessThanOrEqual(20)
					.codePoints(ASCII_PRINTABLE_CHARS).asWhiteList())
			.constraint(User::email, "email", c -> c
					.notBlank()
					.lessThanOrEqual(50)
					.email())
			.constraint(User::age, "age", c -> c
					.notNull()
					.greaterThanOrEqual(0)
					.lessThanOrEqual(200))
			.build()
			.applicative();
}
