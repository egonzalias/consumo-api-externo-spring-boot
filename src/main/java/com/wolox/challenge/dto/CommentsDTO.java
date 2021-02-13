package com.wolox.challenge.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String postId;
    private String id;
    private String name;
    private String email;
    private String body;
    
}
