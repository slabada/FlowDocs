package com.flowdocs.repository;

import com.flowdocs.model.DocumentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentModel, Long> {

}
