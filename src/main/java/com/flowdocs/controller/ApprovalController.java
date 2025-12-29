package com.flowdocs.controller;

import com.flowDocs.api.ApprovalApi;
import com.flowDocs.model.ApprovalDecision;
import com.flowDocs.model.ApprovalDto;
import com.flowdocs.domain.ApprovalDomain;
import com.flowdocs.mapper.ApprovalMapper;
import com.flowdocs.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApprovalController implements ApprovalApi {

    private final ApprovalService approvalService;
    private final ApprovalMapper approvalMapper;

    @Override
    public ResponseEntity<ApprovalDto> createApprove(Long id, ApprovalDecision approvalDecision) {
        ApprovalDomain domain = approvalService.createApprove(id, approvalDecision.getValue());
        ApprovalDto dto = approvalMapper.toDto(domain);
        return ResponseEntity.ok().body(dto);
    }

    @Override
    public ResponseEntity<ApprovalDto> updateApprove(Long id, ApprovalDecision approvalDecision) {
        ApprovalDomain domain = approvalService.updateApprove(id, approvalDecision.getValue());
        ApprovalDto dto = approvalMapper.toDto(domain);
        return ResponseEntity.ok().body(dto);
    }
}
