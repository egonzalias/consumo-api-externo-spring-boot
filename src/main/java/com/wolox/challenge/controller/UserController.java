package com.wolox.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "/album/permissions")
	public String userAlbumPermissions(@RequestParam(name = "nameAlbum", required = true) String nameAlbum,
			@RequestParam(name = "read", required = false) String read,
			@RequestParam(name = "write", required=false) String write) {

		return "Hello userAlbumPermissions!";
	}

	@GetMapping(value = "/change/user/permissions")
	public String changeUserPermissions(@RequestParam(name = "nameAlbum", required = true) String nameAlbum,
			@RequestParam(name = "read", required = false) String read,
			@RequestParam(name = "write", required=false) String write) {

		return "Hello userAlbumPermissions!";
	}
}
