package com.projet.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class EntrAD {


    @Id
    @Column(name = "id")
    public long id=1;


    public String rue;

    @OneToMany(cascade = CascadeType.ALL)
    public Set<User> user;



    public String ville;


    public String pays;

    public String email;


    public String zipCode;



    public String tel;



    public long numPatente;

    public String RC;

    public String CNSS;

    public long Fid;
}