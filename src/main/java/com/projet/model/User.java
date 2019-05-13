package com.projet.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {

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

    @Size(max = 30)
    private String roleCabinet;

    private String password;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Consultation> consultation;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Produit> produit;

    @Embedded
    private Address address;

    @CreatedDate
    private LocalDate createdDate;

    @LastModifiedDate
    private LocalDate lastmodifiedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    private EntrAD entrAD;

    public User(){}

    public User(@Size(max = 100) String email, @Size(max = 100) String firstName, @Size(max = 100) String lastName, String password, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.address = address;
    }

    public Set<Consultation> getConsultation() {
        return consultation;
    }

    public void setConsultation(Set<Consultation> consultation) {
        this.consultation = consultation;
    }

    public Set<Produit> getProduit() {
        return produit;
    }

    public void setProduit(Set<Produit> produit) {
        this.produit = produit;
    }

    public EntrAD getEntrAD() {
        return entrAD;
    }

    public void setEntrAD(EntrAD entrAD) {
        this.entrAD = entrAD;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleCabinet() {
        return roleCabinet;
    }

    public void setRoleCabinet(String roleCabinet) {
        this.roleCabinet = roleCabinet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getLastmodifiedDate() {
        return lastmodifiedDate;
    }

    public void setLastmodifiedDate(LocalDate lastmodifiedDate) {
        this.lastmodifiedDate = lastmodifiedDate;
    }
}
