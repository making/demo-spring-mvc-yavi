package com.example.yavi.web;

import am.ik.yavi.core.Validator;
import com.example.yavi.domain.User;

import static am.ik.yavi.constraint.charsequence.codepoints.AsciiCodePoints.ASCII_PRINTABLE_CHARS;

public interface UserForm {
    String getName();

    String getEmail();

    Integer getAge();

    Validator<UserForm> validator = Validator.builder(UserForm.class)
            .constraint(UserForm::getName, "name", //
                    c -> c.notBlank().lessThanOrEqual(20)
                            .codePoints(ASCII_PRINTABLE_CHARS).allIncluded())
            .constraint(UserForm::getEmail, "email", //
                    c -> c.notBlank().lessThanOrEqual(50).email())
            .constraint(UserForm::getAge, "age", //
                    c -> c.notNull().greaterThanOrEqual(0).lessThanOrEqual(200))
            .build();

    default User toUser() {
        return new User(getName(), getEmail(), getAge());
    }
}
