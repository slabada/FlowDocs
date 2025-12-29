package com.flowdocs.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserDomain extends BaseDomain {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String role;
}
