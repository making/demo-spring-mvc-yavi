package com.example.yavi.domain;

import am.ik.yavi.meta.ConstraintArguments;

public class User {

    private final String name;

    private final String email;

    private final Integer age;

    @ConstraintArguments
    User(String name, String email, Integer age) {
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
