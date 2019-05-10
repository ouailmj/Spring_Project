package com.projet.model;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
@EntityListeners(AuditingEntityListener.class)
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Size(max = 100)
    private String email;

    @Size(max = 100)
    private String firstName;

    @Size(max = 100)
    private String lastName;

    private boolean sexe;

    @Temporal(value = TemporalType.DATE)
    private Date birthDate;

    @Embedded
    Address address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Facture> facture;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Consultation> consultation;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastmodifiedDate;

}
