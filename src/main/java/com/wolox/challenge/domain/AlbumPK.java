package com.wolox.challenge.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class AlbumPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name = "album_id")
	private String album_id;
	
	@Column(name = "album_owner")
	private String album_owner;
	
	@Column(name = "user_id")
    private String user_id;	
	
}
