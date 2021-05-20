package com.example.yavi.domain;

import am.ik.yavi.builder.ValidatorBuilder;
import am.ik.yavi.core.Validated;
import am.ik.yavi.core.Validator;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public class User {

    private final String name;

    private final String email;

    private final Integer age;

    private static final Validator<User> validator = ValidatorBuilder.<User>of()
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
            .build();

    User(String name, String email, Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public static Validated<User> of(String name, String email, Integer age) {
        return validator.applicative().validate(new User(name, email, age));
    }

    public String name() {
        return this.name;
    }

    public String email() {
        return this.email;
    }

    public Integer age() {
        return this.age;
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
