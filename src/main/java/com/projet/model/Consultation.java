package com.projet.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "consultations")
public class Consultation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Embedded
    private Mesure mesure;

    private String type;

    private double prix;

    @OneToOne
    private Facture facture;

    @ManyToOne
    @JoinColumn
    private Client client;

    @ManyToOne
    @JoinColumn
    private User user;

    @ManyToMany
    @JoinTable(name="consultation_produit", joinColumns=@JoinColumn(name="consultation_id"), inverseJoinColumns=@JoinColumn(name="produit_id"))
    private Set<Produit> produits;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastmodifiedDate;

}
