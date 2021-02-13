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
import com.wolox.challenge.domain.Album;
import com.wolox.challenge.domain.AlbumPK;
import com.wolox.challenge.dto.AlbumDTO;
import com.wolox.challenge.dto.UserDTO;
import com.wolox.challenge.exception.GeneralException;
import com.wolox.challenge.exception.ResourceNotFoundException;
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
	public AlbumDTO registerSharedAlbum(AlbumDTO albumDtoData) throws Exception {

		try {
			String album_id = "/" + albumDtoData.getAlbum_id();
			String response = camel.sendRequest(url + album + album_id);

			if (response != null && !response.isEmpty()) {
				AlbumDTO objAlbumDto = new ObjectMapper().readValue(response, AlbumDTO.class);
				if (objAlbumDto.getId() != null) {
					String user_id = "/" + albumDtoData.getUser_id();
					response = camel.sendRequest(url + users + user_id);
					if (response != null && !response.isEmpty()) {

						UserDTO userTargetDto = new ObjectMapper().readValue(response, UserDTO.class);

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
							throw new ResourceNotFoundException("El usuario ["+user_id+"] no existe en el API externo.");
						}
					} else {
						// Error respuesta en consulta usuario
						throw new GeneralException("Error obteniendo informacion de usuarios desde del API externo.");
					}
				} else {
					// No existe album
					throw new ResourceNotFoundException("El album ["+album_id+"] no existe en el API externo.");
				}
			} else {
				// Error respuesta en consulta album info
				throw new GeneralException("Error obteniendo informacion de los album desde del API externo.");
			}
			// System.out.println(">>"+response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		return null;
	}

	@Override
	public List<UserDTO> getPermissionsUser(String albumId, String permission) throws Exception {
		List<UserDTO> lstUser = new ArrayList<>();
		try {
			// TODO Auto-generated method stub
			List<String> lstIdsUser = new ArrayList<>();

			if (albumId != null && !albumId.isEmpty()) {
				if ((permission != null && permission.equalsIgnoreCase(AlbumDTO.READ))
						|| (permission != null && permission.equalsIgnoreCase(AlbumDTO.WRITE))) {
					lstIdsUser = albumRepository.getPermissionsUser(albumId, permission.toUpperCase());

					if(lstIdsUser != null && lstIdsUser.size() >= 1) {
						String response = camel.sendRequest(url + users);
						if (response != null && !response.isEmpty()) {
							JSONArray jsonArr = new JSONArray(response);
							for (int i = 0; i < jsonArr.length(); i++)
					        {
					            UserDTO userTmp =  new ObjectMapper().readValue(jsonArr.getJSONObject(i).toString(), UserDTO.class);
					            lstUser.add(userTmp);
					        }
							List<String> lstIdsUserTmp = lstIdsUser;						
							lstUser = lstUser.stream().filter(obj -> lstIdsUserTmp.contains(obj.getId())).collect(Collectors.toList());
						}
						else {
							throw new GeneralException("Error obteniendo informacion de usuarios desde del API externo.");
						}
					}
					else {
						//Album no existe
						throw new ResourceNotFoundException("El album ["+albumId+"] no existe en la base de datos.");
					}
										
				} else {
					// permiso no permitido
					throw new GeneralException("El permiso ["+permission+"] no esta permitido. Intente W o R.");
				}

			} else {
				// album erroneo
				throw new GeneralException("El album ["+albumId+"] nulo o vacion no es valido para la busqueda.");
			}
		}catch (Exception e) {
			throw e;
		}
		return lstUser;
	}

}
