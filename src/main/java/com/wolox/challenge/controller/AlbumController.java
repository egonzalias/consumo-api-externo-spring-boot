package com.wolox.challenge.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wolox.challenge.dto.AlbumDTO;
import com.wolox.challenge.dto.UserDTO;
import com.wolox.challenge.service.AlbumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
@Api(value = "/album", tags = "Album media operations")
public class AlbumController {

	private final AlbumService albumService;

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 500, message = "Failure") })
	@PutMapping(value = "/register/shared", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AlbumDTO> registerSharedAlbum(@RequestBody AlbumDTO albumData) throws Exception {
		albumService.registerSharedAlbum(albumData);
		return new ResponseEntity<AlbumDTO>(new AlbumDTO(), HttpStatus.OK);
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 500, message = "Failure") })
	@GetMapping(value = "/permissions/user")
	public ResponseEntity<List<UserDTO>> getPermissionsUser(@RequestParam(name = "id", required = true) String id,
			@RequestParam(name = "permission", required = true) String permission) throws Exception {
		List<UserDTO>  lstUsers = albumService.getPermissionsUser(id,permission);
		return new ResponseEntity<List<UserDTO>>(lstUsers, HttpStatus.OK);
	}
}
