package com.wolox.challenge.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	/*@Mapping(target = "address", ignore = true)
	@Mapping(target = "company", ignore = true)
	UserDTO userToUserDto (User user);
	
	@Mapping(target = "address", ignore = true)
	@Mapping(target = "company", ignore = true)
	User userDtoToUser (UserDTO user);*/
}
