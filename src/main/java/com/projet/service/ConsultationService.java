package com.projet.service;

import com.projet.model.Client;
import com.projet.model.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ConsultationService {
    public Page<Consultation> findAll(PageRequest pageRequest, int page);

    public Consultation findConsultation(long id);

    public Boolean updateConsultation(Consultation consultation);

    public Boolean createConsultation(Consultation consultation);
}
