package com.wolox.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value = "/user", tags = "Users media operations")
public class UserController {

	

	@GetMapping(value = "/test")
	public String test() {
		//producer.sendBody("Edwin!");
		return "Hello World!";
	}

}
