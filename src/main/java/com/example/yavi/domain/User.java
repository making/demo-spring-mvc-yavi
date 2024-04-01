package com.example.yavi.domain;

import am.ik.yavi.arguments.Validator3;
import am.ik.yavi.validator.Yavi;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public record User(String name, String email, Integer age) {

	public static final Validator3<String, String, Integer, User> validator = Yavi.validator()
			._string("name", c -> c
					.notBlank()
					.emoji()
					.lessThanOrEqual(20)
					.codePoints(ASCII_PRINTABLE_CHARS).asWhiteList())
			._string("email", c -> c
					.notBlank()
					.lessThanOrEqual(50)
					.email())
			._integer("age", c -> c
					.notNull()
					.greaterThanOrEqual(0)
					.lessThanOrEqual(200))
			.apply(User::new);
}
