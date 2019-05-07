package com.projet.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Address {

    @NotNull
    @Size(max = 100)
    private String rue;

    @NotNull
    @Size(max = 100)
    private String ville;

    @NotNull
    @Size(max = 100)
    private String pays;

    @NotNull
    @Size(max = 6)
    private String zipCode;

    @Nullable
    @Size(max = 12)
    private String tel;

}
