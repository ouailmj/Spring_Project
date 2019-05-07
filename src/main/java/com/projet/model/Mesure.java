package com.projet.model;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mesure {

    private double sphereOD;
    private double sphereOG;

    private double cylindreOD;
    private double cylindreOG;

    private double axeOG;
    private double axeOD;

    private double addOD;
    private double addOG;


}
