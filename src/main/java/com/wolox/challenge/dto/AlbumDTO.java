package com.wolox.challenge.dto;

import java.io.Serializable;

import com.wolox.challenge.domain.AlbumPK;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumDTO implements Serializable {
	/**
	* 
	*/

	public static final String WRITE = "W";
	public static final String READ = "R";
	private static final long serialVersionUID = 1L;
	// From database
	private AlbumPK idPK;	
	private String album_id;
	private String album_owner;
	private boolean read;
	private boolean write;
	private String user_id;

	// From API
	private String userId;
	private String id;
	private String title;

}
