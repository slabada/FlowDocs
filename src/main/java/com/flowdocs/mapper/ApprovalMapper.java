package com.flowdocs.mapper;

import com.flowDocs.model.ApprovalDto;
import com.flowdocs.domain.ApprovalDomain;
import com.flowdocs.model.ApprovalModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ApprovalMapper {

    @Mapping(target = "expert", ignore = true)
    @Mapping(target = "documentId", ignore = true)
    @Mapping(target = "decision", ignore = true)
    @Mapping(target = "decidedAt", ignore = true)
    ApprovalDto toDto(ApprovalModel dto);

    List<ApprovalDto> toDto(List<ApprovalModel> dtos);

    ApprovalDto toDto(ApprovalDomain domain);

    ApprovalModel toModel(ApprovalDto model);

    List<ApprovalModel> toModel(List<ApprovalDto> models);

    ApprovalModel toModel(ApprovalDomain domain);

    ApprovalDomain toDomain(ApprovalModel model);
}
