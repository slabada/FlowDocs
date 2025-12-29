package com.flowdocs.domain;

import com.flowdocs.enums.ApprovalDecision;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ApprovalDomain extends BaseDomain {

    private Long documentId;

    private UserDomain expert;

    private ApprovalDecision decision;

    private OffsetDateTime decidedAt;

}
