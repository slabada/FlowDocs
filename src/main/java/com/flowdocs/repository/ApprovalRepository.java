package com.flowdocs.repository;

import com.flowdocs.model.ApprovalModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalModel, Long> {
}
