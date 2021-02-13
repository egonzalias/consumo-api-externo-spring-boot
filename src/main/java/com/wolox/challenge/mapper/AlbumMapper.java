package com.wolox.challenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.wolox.challenge.domain.Album;
import com.wolox.challenge.dto.AlbumDTO;

@Mapper(componentModel = "spring")
public interface AlbumMapper {
	
	AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

	AlbumDTO albumToAlbumDto (Album album);

	Album albumDtoToAlbum (AlbumDTO album);
}
