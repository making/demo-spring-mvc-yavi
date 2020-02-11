package com.example.yavi.web;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.fn.Either;
import com.example.yavi.domain.User;
import com.example.yavi.domain.UserValidator;

public interface UserForm {

    String getName();

    String getEmail();

    Integer getAge();

    default Either<ConstraintViolations, User> validate() {
        return UserValidator.SINGLETON.validateArgs(getName(), getEmail(), getAge());
    }
}
