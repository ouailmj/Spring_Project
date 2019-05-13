package com.projet.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "produits")
@EntityListeners(AuditingEntityListener.class)
public class Produit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String designation;

    private String type;

    private double prix;

    private int indice;

    private double diametre;

    private double qte;

    @ManyToOne
    @JoinColumn
    private User user;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastmodifiedDate;

}
