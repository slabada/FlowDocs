package com.flowdocs.service;

import com.flowdocs.configuration.SecurityConfig;
import com.flowdocs.domain.ApprovalDomain;
import com.flowdocs.domain.DocumentDomain;
import com.flowdocs.domain.UserDomain;
import com.flowdocs.enums.ApprovalDecision;
import com.flowdocs.exception.ApprovalException;
import com.flowdocs.mapper.ApprovalMapper;
import com.flowdocs.model.ApprovalModel;
import com.flowdocs.repository.ApprovalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ApprovalServiceImpl implements ApprovalService {

    private final DocumentService documentService;
    private final UserService userService;
    private final ApprovalRepository approvalRepository;
    private final ApprovalMapper approvalMapper;

    @Override
    public ApprovalDomain createApprove(Long id, String approvalDecision) {
        try {
            DocumentDomain document = documentService.getDocumentById(id);
            Long currentUserId = Objects.requireNonNull(SecurityConfig.getUserProxy()).getId();
            validateExpertForDocument(document, currentUserId);
            UserDomain user = userService.getUser(currentUserId);
            ApprovalDomain approval = new ApprovalDomain().toBuilder()
                    .documentId(document.getId())
                    .expert(user)
                    .decision(ApprovalDecision.valueOf(approvalDecision))
                    .decidedAt(OffsetDateTime.now())
                    .build();
            document.setApprovals(List.of(approval));
            ApprovalModel model = approvalMapper.toModel(approval);
            ApprovalModel save = approvalRepository.save(model);
            documentService.createDocument(id, document);
            return approvalMapper.toDomain(save);
        } catch (DataIntegrityViolationException e) {
            throw new ApprovalException.ConflictApprovalException();
        }
    }

    @Override
    public ApprovalDomain updateApprove(Long id, String approvalDecision) {
        DocumentDomain document = documentService.getDocumentById(id);
        Long currentUserId = Objects.requireNonNull(SecurityConfig.getUserProxy()).getId();
        validateExpertForDocument(document, currentUserId);
        ApprovalDomain approval = document.getApprovals().stream()
                .filter(a -> a.getExpert().getId().equals(currentUserId))
                .findFirst()
                .orElseThrow(ApprovalException.NonApprovalException::new);
        approval.setDecision(ApprovalDecision.valueOf(approvalDecision));
        ApprovalModel model = approvalMapper.toModel(approval);
        ApprovalModel save = approvalRepository.save(model);
        return approvalMapper.toDomain(save);
    }

    private static void validateExpertForDocument(DocumentDomain document, Long currentUserId) {
        boolean isExpert = document.getExperts().stream()
                .anyMatch(expert -> expert.getId().equals(currentUserId));
        if(!isExpert) {
            throw new ApprovalException.NonExpertException();
        }
    }
}
