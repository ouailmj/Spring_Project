package com.projet.service;

import com.projet.model.Facture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface FactureService {

    public Page<Facture> findAll(PageRequest pageRequest, int page);

}