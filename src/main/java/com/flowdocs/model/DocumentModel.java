package com.flowdocs.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class DocumentModel extends BaseModel {

    public String name;

    public String version;
}
