package com.example.yavi.web;

import com.example.yavi.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class UserController {

    private final List<User> users = new CopyOnWriteArrayList<>();

    @GetMapping("/")
    public String users(Model model, UserForm userForm) {
        model.addAttribute("userForm", userForm);
        model.addAttribute("users", this.users);
        return "users";
    }

    @PostMapping("/")
    public String createUser(Model model, UserForm userForm, BindingResult result) {
        return userForm.validate()
            .fold(violations -> {
                violations.apply(result::rejectValue);
                return this.users(model, userForm);
            }, user -> {
                this.users.add(user);
                return "redirect:/";
            });
    }

}
