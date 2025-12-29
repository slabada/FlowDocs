package com.flowdocs.controller;

import com.flowDocs.api.DocumentApi;
import com.flowDocs.model.DocumentDto;
import com.flowDocs.model.RequestDocumentDto;
import com.flowdocs.domain.DocumentDomain;
import com.flowdocs.mapper.DocumentMapper;
import com.flowdocs.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentController implements DocumentApi {

    private final DocumentService documentService;

    private final DocumentMapper documentMapper;

    @Override
    public ResponseEntity<DocumentDto> getDocument(Long id) {
        DocumentDomain document = documentService.getDocumentById(id);
        DocumentDto dto = documentMapper.toDto(document);
        return ResponseEntity.ok().body(dto);
    }

    @Override
    public ResponseEntity<DocumentDto> createDocument(RequestDocumentDto requestDocumentDto) {
        DocumentDomain domain = documentMapper.toDomain(requestDocumentDto);
        DocumentDomain newDocument = documentService.createDocument(null, domain);
        DocumentDto dto = documentMapper.toDto(newDocument);
        return ResponseEntity.ok().body(dto);
    }

    @Override
    public ResponseEntity<DocumentDto> updateDocument(Long id, RequestDocumentDto requestDocumentDto) {
        DocumentDomain domain = documentMapper.toDomain(requestDocumentDto);
        DocumentDomain newDocument = documentService.updateDocument(id, domain);
        DocumentDto dto = documentMapper.toDto(newDocument);
        return ResponseEntity.ok().body(dto);
    }
}
