package com.flowdocs.service;

import com.flowdocs.domain.DocumentDomain;
import com.flowdocs.domain.UserDomain;
import com.flowdocs.exception.DocumentException;
import com.flowdocs.mapper.DocumentMapper;
import com.flowdocs.mapper.UserMapper;
import com.flowdocs.model.DocumentModel;
import com.flowdocs.model.UserModel;
import com.flowdocs.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final UserService userService;

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    private final UserMapper userMapper;

    @Override
    public DocumentDomain getDocumentById(Long id) {
        DocumentModel document = documentRepository.findById(id)
                .orElseThrow(DocumentException.NullDocumentException::new);
        return documentMapper.toDomain(document);
    }

    @Override
    public DocumentDomain createDocument(Long id, DocumentDomain domain) {
        List<UserModel> authors = domain.getAuthors().stream()
                .map(UserDomain::getId)
                .map(userService::getUser)
                .map(userMapper::toModel)
                .toList();
        List<UserModel> experts = domain.getExperts().stream()
                .map(UserDomain::getId)
                .map(userService::getUser)
                .map(userMapper::toModel)
                .toList();
        DocumentModel newDocument = new DocumentModel().toBuilder()
                .name(domain.getName())
                .description(domain.getDescription())
                .dateStart(domain.getDateStart())
                .dateEnd(domain.getDateEnd())
                .authors(authors)
                .experts(experts)
                .build();
        DocumentModel save = documentRepository.save(newDocument);
        return documentMapper.toDomain(save);
    }

    @Override
    public DocumentDomain updateDocument(Long id, DocumentDomain domain) {
        DocumentModel model = documentRepository.findById(id)
                .orElseThrow(DocumentException.NullDocumentException::new);
        List<UserModel> authors = domain.getAuthors().stream()
                .map(UserDomain::getId)
                .map(userService::getUser)
                .map(userMapper::toModel)
                .collect(Collectors.toList());
        List<UserModel> experts = domain.getExperts().stream()
                .map(UserDomain::getId)
                .map(userService::getUser)
                .map(userMapper::toModel)
                .collect(Collectors.toList());
        documentMapper.updateDocument(domain, model);
        model.setAuthors(authors);
        model.setExperts(experts);
        DocumentModel save = documentRepository.save(model);
        return documentMapper.toDomain(save);
    }
}
