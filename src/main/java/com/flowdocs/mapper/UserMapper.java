package com.flowdocs.mapper;

import com.flowDocs.model.UserDto;
import com.flowdocs.model.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserModel entity);

}
