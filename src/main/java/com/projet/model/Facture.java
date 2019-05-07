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
@Table(name = "factures")
@EntityListeners(AuditingEntityListener.class)
public class Facture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String etat;

    private double montantTotal;

    private double montantPaye;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastmodifiedDate;

    @ManyToOne
    @JoinColumn
    private Client client;

    @OneToOne
    private Consultation consultation;

}
