package com.example.yavi.web;

import com.example.yavi.domain.User;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
				.content("name=+making+&email=Making@Example.com&age=20"))
			.andExpect(status().isFound())
			.andExpect(redirectedUrl("/"));
		assertThat(userController.users).containsExactly(new User("making", "making@example.com", 20));
	}

	@Test
	void users() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(model().attributeHasNoErrors("userForm"));
	}

	@Test
	void createUser_ng_null() throws Exception {
		this.mockMvc.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED).content("name=&email=&age="))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrorCode("userForm", "name", "charSequence.notBlank"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "email", "charSequence.notBlank"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "age", "object.required"));
	}

	@Test
	void createUser_ng_invalid() throws Exception {
		this.mockMvc
			.perform(post("/").contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("name=012345678901234567890&email=maki&age=abc"))
			.andExpect(status().isOk())
			.andExpect(model().attributeHasFieldErrorCode("userForm", "name", "container.lessThanOrEqual"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "email", "charSequence.email"))
			.andExpect(model().attributeHasFieldErrorCode("userForm", "age", "object.type"))
			.andExpect(content().string(containsString("value=\"abc\"")))
			.andExpect(content().string(containsString("&quot;age&quot; must be of type integer")));
	}

}
