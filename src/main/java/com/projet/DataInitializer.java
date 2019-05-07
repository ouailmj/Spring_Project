package com.projet;


import com.projet.model.*;
import com.projet.repository.ClientRepository;
import com.projet.repository.ConsultationRepository;
import com.projet.repository.FactureRepository;
import com.projet.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProduitRepository produitRepository;
    private final ClientRepository clientRepository;
    private final FactureRepository factureRepository;
    private final ConsultationRepository consultationRepository;


    @Autowired
    public DataInitializer(ProduitRepository produitRepository, ClientRepository clientRepository, FactureRepository factureRepository, ConsultationRepository consultationRepository) {
        this.produitRepository = produitRepository;
        this.clientRepository = clientRepository;
        this.factureRepository = factureRepository;
        this.consultationRepository = consultationRepository;
    }


    @Override
    public void run(String... args) throws Exception {


        Produit produit = Produit.builder().designation("produit1").type("type1").indice(1)
                .prixOD(12).prixOG(14).diametre(20).build();
        Produit produit1 = Produit.builder().designation("produit2").type("type2").indice(2)
                .prixOD(12).prixOG(14).diametre(20).build();

        Set<Produit> produits = new HashSet<Produit>();
        produits.add(produit);
        produits.add(produit1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Client client = Client.builder().email("aniss@gmail.com").firstName("aniss")
                .lastName("mbarki").sexe(true).birthDate(dateFormat.parse("1997-12-29")).build();
        Address address = Address.builder().rue("19").pays("maroc").ville("marrakech").tel("0678464545").zipCode("1234").build();
        Facture facture = Facture.builder().etat("payé").montantPaye(134).montantTotal(134).build();
        Mesure mesure = new Mesure(12,12,12,12,12,12,12,12);
        Consultation consultation = Consultation.builder().mesure(mesure).type("VP").facture(facture)
                .client(client).produits(produits).build();

        this.produitRepository.save(produit);
        this.produitRepository.save(produit1);
        this.clientRepository.save(client);
        this.factureRepository.save(facture);
        this.consultationRepository.save(consultation);


    }
}
