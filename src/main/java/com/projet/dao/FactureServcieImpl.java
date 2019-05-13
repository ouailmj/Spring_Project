package com.projet.dao;

import com.projet.model.Facture;
import com.projet.repository.FactureRepository;
import com.projet.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("factureService")
public class FactureServcieImpl implements FactureService {
    @Autowired
    FactureRepository factureRepository;

    @Override
    public Page<Facture> findAll(PageRequest pageRequest, int page) {
        return factureRepository.findAll(new PageRequest(page,10, Sort.by("id").descending()));
    }
}