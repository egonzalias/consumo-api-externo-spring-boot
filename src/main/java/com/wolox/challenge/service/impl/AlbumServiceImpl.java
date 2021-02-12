package com.wolox.challenge.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolox.challenge.camel.ApiBuilder;
import com.wolox.challenge.domain.Album;
import com.wolox.challenge.domain.AlbumPK;
import com.wolox.challenge.dto.AlbumDTO;
import com.wolox.challenge.dto.UserDTO;
import com.wolox.challenge.mapper.AlbumMapper;
import com.wolox.challenge.repository.AlbumRepository;
import com.wolox.challenge.service.AlbumService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlbumServiceImpl implements AlbumService {

	/*
	 * @Produce(value = "direct:consumirWS") private ProducerTemplate producer;
	 */

	@Value("${url.external.api}")
	private String url;

	@Value("${url.external.api.endpoint.albums}")
	private String album;

	@Value("${url.external.api.endpoint.users}")
	private String users;

	@Autowired
	private ApiBuilder camel;

	@Autowired
	private AlbumRepository albumRepository;

	private final AlbumMapper albumMapper;

	@Override
	public AlbumDTO registerSharedAlbum(AlbumDTO albumDtoData) {

		try {
			String album_id = "/" + albumDtoData.getAlbum_id();
			String response = camel.sendRequest(url + album + album_id);

			if (response != null && !response.isEmpty()) {
				AlbumDTO objAlbumDto = new ObjectMapper().readValue(response, AlbumDTO.class);
				if (objAlbumDto.getId() != null) {
					String user_id = "/" + albumDtoData.getUser_id();
					response = camel.sendRequest(url + users + user_id);
					if (response != null && !response.isEmpty()) {

						UserDTO userTargetDto = new ObjectMapper()
								.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
								.readValue(response, UserDTO.class);

						if (userTargetDto.getId() != null) {
							AlbumPK albumPK = new AlbumPK();
							albumPK.setAlbum_id(objAlbumDto.getId());
							albumPK.setAlbum_owner(objAlbumDto.getUserId());
							albumPK.setUser_id(userTargetDto.getId());
							albumDtoData.setIdPK(albumPK);

							Album album = albumMapper.albumDtoToAlbum(albumDtoData);
							albumRepository.saveAndFlush(album);
						} else {
							// No existe usuario destino
						}
					} else {
						// Error respuesta en consulta usuario
					}
				} else {
					// No existe album
				}
			} else {
				// Error respuesta en consulta album info
			}
			// System.out.println(">>"+response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// System.out.println(albumDto.getUser_id());
		return null;
	}

	@Override
	public List<UserDTO> getPermissionsUser(String albumId, String permission) {
		List<UserDTO> lstUser = new ArrayList<>();
		try {
			// TODO Auto-generated method stub
			List<String> lstIdsUser = new ArrayList<>();
			List<Album> lstAlbum = new ArrayList<>();
			String field = "";
			boolean blState = false;

			if (albumId != null && !albumId.isEmpty()) {
				if ((permission != null && permission.equalsIgnoreCase(AlbumDTO.READ))
						|| (permission != null && permission.equalsIgnoreCase(AlbumDTO.WRITE))) {
					lstIdsUser = albumRepository.getPermissionsUser(albumId, permission);
					
					String response = camel.sendRequest(url + users);
					if (response != null && !response.isEmpty()) {
						JSONArray jsonArr = new JSONArray(response);
						
						for (int i = 0; i < jsonArr.length(); i++)
				        {
				            JSONObject jsonObj = jsonArr.getJSONObject(i);
				            UserDTO userTmp = new UserDTO();
				            userTmp.setId(String.valueOf(jsonObj.getInt("id")));
				            userTmp.setName(jsonObj.getString("name"));
				            userTmp.setUsername(jsonObj.getString("username"));
				            lstUser.add(userTmp);
				        }
						List<String> lstIdsUserTmp = lstIdsUser;	
						
						lstUser = lstUser.stream().filter(obj -> lstIdsUserTmp.contains(obj.getId())).collect(Collectors.toList());
					}
					
					
				} else {
					// permiso no permitido
				}

			} else {
				// album erroneo
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Error:"+e.getMessage());
		}
		return lstUser;
	}

}
