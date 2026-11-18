package com.example.yavi.web;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import am.ik.yavi.core.ConstraintViolations;
import am.ik.yavi.core.StringCoercion;
import am.ik.yavi.core.Validated;
import am.ik.yavi.core.ValueValidator;
import am.ik.yavi.input.ParameterTree;
import com.example.yavi.domain.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	static final ValueValidator<Map<String, ?>, User> formValidator = User.SCHEMA
		.readingParameters(StringCoercion.strict().trimming());

	final List<User> users = new CopyOnWriteArrayList<>();

	// The submitted parameters are the form object: the template shows them again
	@ModelAttribute
	void userForm(@RequestParam Map<String, String> params, Model model) {
		Map<String, Object> form = ParameterTree.of(params).toParameters(formValidator.description());
		model.addAllAttributes(new MapBindingResult(form, "userForm").getModel());
	}

	@GetMapping("/")
	public String users(Model model) {
		model.addAttribute("users", this.users);
		return "users";
	}

	@PostMapping("/")
	public String createUser(@RequestParam MultiValueMap<String, String> params, BindingResult result, Model model) {
		return switch (formValidator.validate(params)) {
			case Validated.Valid<User>(User user) -> {
				this.users.add(user);
				yield "redirect:/";
			}
			case Validated.Invalid<User>(ConstraintViolations violations) -> {
				violations.apply(result::rejectValue);
				yield this.users(model);
			}
		};
	}

}
