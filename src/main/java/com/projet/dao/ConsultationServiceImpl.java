package com.projet.dao;

import com.projet.model.Client;
import com.projet.model.Consultation;
import com.projet.model.Mesure;
import com.projet.repository.ConsultationRepository;
import com.projet.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("consultationService")
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

    @Override
    public Page<Consultation> findAll(PageRequest pageRequest, int page) {
        return consultationRepository.findAll(new PageRequest(page,10, Sort.by("id").descending()));
    }

    @Override
    public Consultation findConsultation(long id) {
        return consultationRepository.findById(id).get();
    }

    @Override
    public Boolean updateConsultation(Consultation consultation) {
        try {
            Consultation updatedConsultation = this.findConsultation(consultation.getId());
            Mesure mesure=new Mesure();
            mesure.setAddOD(consultation.getMesure().getAddOD());
            mesure.setAddOG(consultation.getMesure().getAddOG());
            mesure.setAxeOD(consultation.getMesure().getAxeOD());
            mesure.setAxeOG(consultation.getMesure().getAxeOG());
            mesure.setCylindreOD(consultation.getMesure().getCylindreOD());
            mesure.setCylindreOG(consultation.getMesure().getCylindreOG());
            mesure.setSphereOD(consultation.getMesure().getSphereOD());
            mesure.setSphereOG(consultation.getMesure().getSphereOG());
            updatedConsultation.setMesure(mesure);
            updatedConsultation.setType(consultation.getType());
            updatedConsultation.setPrix(consultation.getPrix());
            consultationRepository.save(updatedConsultation);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Boolean createConsultation(Consultation consultation) {
        //not yet
        return null;
    }
}
