package com.projet.repository;


import com.projet.model.Client;
import com.projet.model.Facture;
import com.projet.model.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureRepository extends JpaRepository<Facture,Long> {

}