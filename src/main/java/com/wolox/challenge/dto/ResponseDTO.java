package com.wolox.challenge.dto;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDTO {

	private String status;
	private Object data;
	private String error;
	private Enum<HttpStatus> statusCode;

}
