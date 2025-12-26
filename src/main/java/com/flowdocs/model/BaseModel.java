package com.flowdocs.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Data
@EqualsAndHashCode
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
}
