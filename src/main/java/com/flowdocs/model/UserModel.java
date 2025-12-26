package com.flowdocs.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Table(name = "Users")
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends BaseModel {

    public String firstName;

    public String lastName;

    public String email;

    public String password;

    public String role;
}
