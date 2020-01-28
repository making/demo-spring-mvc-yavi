package com.example.yavi.web;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.fn.Either;
import com.example.yavi.domain.User;

public interface UserForm {

    String getName();

    String getEmail();

    Integer getAge();

    default Either<ConstraintViolations, User> validate() {
        return User.validator.validateArgs(getName(), getEmail(), getAge());
    }
}
