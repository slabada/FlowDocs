package com.flowdocs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class CardModel extends BaseModel {

    public String name;

    public String description;

    public OffsetDateTime dateStart;

    public OffsetDateTime dateEnd;

    @ManyToMany
    public List<UserModel> users;
}
