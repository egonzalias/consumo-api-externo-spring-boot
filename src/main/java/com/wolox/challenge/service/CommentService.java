package com.wolox.challenge.service;

import java.util.List;

import com.wolox.challenge.dto.CommentsDTO;


public interface CommentService {

	List<CommentsDTO> getComments (String name) throws Exception;
}
