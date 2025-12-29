package com.flowdocs.model;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class DocumentFileModel extends BaseModel {

    private String name;

    private String version;
}
