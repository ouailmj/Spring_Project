package com.projet.repository;


import com.projet.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends PagingAndSortingRepository<Consultation,Long> {
}
