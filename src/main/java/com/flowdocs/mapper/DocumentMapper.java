package com.flowdocs.mapper;

import com.flowDocs.model.DocumentDto;
import com.flowDocs.model.RequestDocumentDto;
import com.flowdocs.domain.DocumentDomain;
import com.flowdocs.model.DocumentModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",  uses = {ApprovalMapper.class, UserMapper.class})
public interface DocumentMapper {

    DocumentDto toDto(DocumentDomain domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approvals", ignore = true)
    DocumentDomain toDomain(RequestDocumentDto dto);

    DocumentDomain toDomain(DocumentModel model);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "approvals", ignore = true)
    void updateDocument(DocumentDomain domain, @MappingTarget DocumentModel model);
}
