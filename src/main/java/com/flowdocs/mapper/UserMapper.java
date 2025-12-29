package com.flowdocs.mapper;

import com.flowDocs.model.RequestId;
import com.flowDocs.model.UserDto;
import com.flowdocs.domain.UserDomain;
import com.flowdocs.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserModel model);

    List<UserDto> toDto(List<UserModel> model);

    UserDto toDto(UserDomain domain);

    @Mapping(target = "password", ignore = true)
    UserModel toModel(UserDto dto);

    UserModel toModel(UserDomain domain);

    List<UserModel> toModel(List<UserDto> dto);

    UserDomain toDomain(UserModel model);

    @Mapping(target = "password", ignore = true)
    UserDomain toDomain(UserDto dto);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "email", ignore = true)
    UserDomain toDomain(RequestId id);
}
