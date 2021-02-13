package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.dto.CommentsDTO;
import com.wolox.challenge.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Api(value = "/comment", tags = "Comments  operations")
public class CommentController {

	private final CommentService commentService;

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "/get/comments")
	public ResponseEntity<List<CommentsDTO>> getComments(@RequestParam(name = "name", required = true) String name) throws Exception {
		List<CommentsDTO> lstComments = commentService.getComments(name);
		return new ResponseEntity<List<CommentsDTO>>(lstComments, HttpStatus.OK);
	}
}
