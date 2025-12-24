package com.flowdocs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Table(name = "Users")
public class UserModel extends BaseModel {

    public String firstName;

    public String lastName;

    public String role;
}
