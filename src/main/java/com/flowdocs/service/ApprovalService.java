package com.flowdocs.service;

import com.flowdocs.domain.ApprovalDomain;

public interface ApprovalService {

    ApprovalDomain createApprove(Long id, String approvalDecision);

    ApprovalDomain updateApprove(Long id, String approvalDecision);
}
