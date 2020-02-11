package com.example.yavi.domain;

import am.ik.yavi.arguments.Arguments3Validator;
import am.ik.yavi.builder.ArgumentsValidatorBuilder;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public class UserValidator {

    public static final Arguments3Validator<String, String, Integer, User> SINGLETON = ArgumentsValidatorBuilder
        .of(User::new)
        .builder(b -> b
            .constraint(_UserArgumentsMeta.NAME, c -> c
                .notBlank()
                .emoji()
                .lessThanOrEqual(20)
                .codePoints(ASCII_PRINTABLE_CHARS).asWhiteList())
            .constraint(_UserArgumentsMeta.EMAIL, c -> c
                .notBlank()
                .lessThanOrEqual(50)
                .email())
            .constraint(_UserArgumentsMeta.AGE, c -> c
                .notNull()
                .greaterThanOrEqual(0)
                .lessThanOrEqual(200)))
        .build();
}
