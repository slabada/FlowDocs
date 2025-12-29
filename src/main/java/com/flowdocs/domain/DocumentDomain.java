package com.flowdocs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDomain extends BaseDomain {

    private String name;

    private String description;

    private OffsetDateTime dateStart;

    private OffsetDateTime dateEnd;

    private List<UserDomain> authors;

    private List<UserDomain> experts;

    private List<ApprovalDomain> approvals;
}
