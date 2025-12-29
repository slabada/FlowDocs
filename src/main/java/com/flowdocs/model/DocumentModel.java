package com.flowdocs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Table(name = "document")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class DocumentModel extends BaseModel {

    private String name;

    private String description;

    private OffsetDateTime dateStart;

    private OffsetDateTime dateEnd;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "document_authors",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> authors;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "document_experts",
            joinColumns = @JoinColumn(name = "document_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserModel> experts;

    @OneToMany(mappedBy = "documentId", fetch = FetchType.LAZY)
    private List<ApprovalModel> approvals;
}
