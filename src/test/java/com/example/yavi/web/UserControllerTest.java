package com.example.yavi.web;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UserController userController;

	@Test
	void createUser_ok() throws Exception {
		this.mockMvc
			.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("name=making&email=making@gmail.com&age=20"))
			.andExpect(status().isFound());
		assertThat(userController.users).hasSize(1);
	}

	@Test
	void createUser_ng_null() throws Exception {
		this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content("name=&email=&age="))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrorCode("userForm", "name", "charSequence.notBlank"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "email", "charSequence.notBlank"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "age", "object.notNull"));
	}

	@Test
	void createUser_ng_invalid() throws Exception {
		this.mockMvc
			.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("name=012345678901234567890&email=maki&age=201"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrorCode("userForm", "name", "container.lessThanOrEqual"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "email", "charSequence.email"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "age", "numeric.lessThanOrEqual"));
	}

}