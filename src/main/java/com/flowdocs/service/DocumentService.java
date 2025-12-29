package com.flowdocs.service;

import com.flowdocs.domain.DocumentDomain;

public interface DocumentService {

    DocumentDomain getDocumentById(Long id);

    DocumentDomain createDocument(Long id, DocumentDomain domain);

    DocumentDomain updateDocument(Long id, DocumentDomain domain);
}
