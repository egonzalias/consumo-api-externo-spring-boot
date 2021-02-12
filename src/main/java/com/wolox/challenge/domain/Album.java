package com.wolox.challenge.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shared_album")
public class Album implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AlbumPK idPK;

	@Column(name = "read")
	private boolean read;

	@Column(name = "write")
	private boolean write;

}
