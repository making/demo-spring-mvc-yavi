package com.example.yavi.domain;

import am.ik.yavi.arguments.Arguments1;
import am.ik.yavi.arguments.Arguments2;
import am.ik.yavi.arguments.Arguments3;
import am.ik.yavi.arguments.Arguments3Validator;
import am.ik.yavi.builder.ArgumentsValidatorBuilder;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public class User {

    private final String name;

    private final String email;

    private final Integer age;

    public static final Arguments3Validator<String, String, Integer, User> validator = ArgumentsValidatorBuilder
        .of(User::new)
        .builder(b -> b
            ._string(Arguments1::arg1, "name",
                c -> c.notBlank().emoji().lessThanOrEqual(20)
                    .codePoints(ASCII_PRINTABLE_CHARS).asWhiteList())
            ._string(Arguments2::arg2, "email",
                c -> c.notBlank().lessThanOrEqual(50).email())
            ._integer(Arguments3::arg3, "age",
                c -> c.notNull().greaterThanOrEqual(0).lessThanOrEqual(200)))
        .build();

    private User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
            "name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", age=" + age +
            '}';
    }
}
