package com.example.yavi.domain;

import am.ik.yavi.input.ObjectInputValidator;
import am.ik.yavi.validator.Yavi;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public record User(String name, String email, Integer age) {

	public static final ObjectInputValidator<User> SCHEMA = Yavi.schema()
		._string("name", c -> c.trim().notBlank().lessThanOrEqual(20).codePoints(ASCII_PRINTABLE_CHARS).asWhiteList())
		._string("email", c -> c.trim().toLowerCase().notBlank().lessThanOrEqual(50).email())
		._integer("age", c -> c.greaterThanOrEqual(0).lessThanOrEqual(200))
		.fromMap(User::new);

}
