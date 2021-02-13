package com.wolox.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolox.challenge.camel.ApiBuilder;
import com.wolox.challenge.dto.CommentsDTO;
import com.wolox.challenge.exception.GeneralException;
import com.wolox.challenge.exception.ResourceNotFoundException;
import com.wolox.challenge.service.CommentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	@Value("${url.external.api}")
	private String url;

	@Value("${url.external.api.endpoint.comments}")
	private String comments;

	@Autowired
	private ApiBuilder camel;

	@Override
	public List<CommentsDTO> getComments(String name) throws Exception {
		// TODO Auto-generated method stub
		List<CommentsDTO> lstComments = new ArrayList<>();
		try {
			if (name != null && !name.isEmpty()) {
				String response = camel.sendRequest(url + comments );
				if (response != null && !response.isEmpty()) {
					JSONArray jsonArr = new JSONArray(response);
					for (int i = 0; i < jsonArr.length(); i++)
			        {
						CommentsDTO comment =  new ObjectMapper().readValue(jsonArr.getJSONObject(i).toString(), CommentsDTO.class);
						lstComments.add(comment);
			        }					
					lstComments = lstComments.stream().filter(obj -> obj.getName().contains(name)).collect(Collectors.toList());
				}
				else {
					//error consultando comentarios
					throw new GeneralException("Error obteniendo informacion de comentarios desde del API externo.");
				}
			}
			else {
				//digite algun comentario
				throw new ResourceNotFoundException("Digite algun texto para realizar la busqueda.");
			}
			
		}catch (Exception e) {
			throw e;
		}
		return lstComments;
	}

}
