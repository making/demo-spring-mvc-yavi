package com.example.yavi.web;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.yavi.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	final List<User> users = new CopyOnWriteArrayList<>();

	final Validator userFormValidator = Validator.forInstanceOf(UserForm.class,
			UserForm.validator.toBiConsumer(Errors::rejectValue));

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(userFormValidator);
	}

	@PostMapping("/")
	public String createUser(Model model, @Validated UserForm userForm, BindingResult result) {
		if (result.hasErrors()) {
			return this.users(model, userForm);
		}
		this.users.add(new User(userForm.name(), userForm.email(), userForm.age()));
		return "redirect:/";
	}

	@GetMapping("/")
	public String users(Model model, UserForm userForm) {
		model.addAttribute("userForm", userForm);
		model.addAttribute("users", this.users);
		return "users";
	}

}
