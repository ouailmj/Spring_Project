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




    }
}
