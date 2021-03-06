package com.wolox.challenge.service;

import java.util.List;

import com.wolox.challenge.dto.AlbumDTO;
import com.wolox.challenge.dto.UserDTO;


public interface AlbumService {

	AlbumDTO registerSharedAlbum(AlbumDTO album) throws Exception;
	List<UserDTO> getPermissionsUser (String albumId, String permission) throws Exception;
}
