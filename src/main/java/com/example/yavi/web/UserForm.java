package com.example.yavi.web;

import am.ik.yavi.core.Validated;
import com.example.yavi.domain.User;

public interface UserForm {

    String getName();

    String getEmail();

    Integer getAge();

    default Validated<User> toUser() {
        return User.of(getName(), getEmail(), getAge());
    }
}
