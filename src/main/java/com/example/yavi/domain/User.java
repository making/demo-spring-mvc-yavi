package com.example.yavi.domain;

import am.ik.yavi.arguments.Arguments3Validator;
import am.ik.yavi.validator.Yavi;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public record User(String name, String email, Integer age) {

	public static final Arguments3Validator<String, String, Integer, User> validator = Yavi.validator()
		._string("name", c -> c.notBlank().lessThanOrEqual(20).codePoints(ASCII_PRINTABLE_CHARS).asWhiteList())
		._string("email", c -> c.notBlank().lessThanOrEqual(50).email())
		._integer("age", c -> c.notNull().greaterThanOrEqual(0).lessThanOrEqual(200))
		.apply(User::new);

}
