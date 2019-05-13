package com.projet.controller;

import com.projet.model.*;
import com.projet.repository.ConsultationRepository;
import com.projet.repository.FactureRepository;
import com.projet.repository.ProduitRepository;
import com.projet.repository.UserRepository;
import com.projet.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ConsultationController {
    @Autowired
    private ConsultationService consultationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ConsultationRepository consultationRepository;

    @RequestMapping(value = "/consultations/consultation/delete/{id}/{id1}", method = GET)
    public String deleteProduit(@PathVariable("id") long id, @PathVariable("id1") long id1) {
        //delete Produit on va utiliser le id1 qui contient le idProduit qu'il faut supprimer!!
        produitRepository.deleteById(id1);

        return "redirect:/consultations/consultation/" + id;
    }


    @RequestMapping(value = "/consultations/consultation/{id}", method = GET)
    public ModelAndView detailConsultation(@PathVariable("id") long id) {

        int i = 0;

        ModelAndView model = new ModelAndView();
        Produit produit = new Produit();
        System.out.println("ha lproduit id--->" + produit.getId());
        Consultation consultation = consultationService.findConsultation(id);
        Set<Produit> produits;
        produits = consultation.getProduits();

        model.addObject("id", id);
        model.addObject("consultation", consultation);
        model.addObject("produitAjoute", produit);
        model.addObject("produits", produits);
        model.addObject("consultations", i);
        List<User> user =userRepository.findAll();
        model.addObject("role",user.get(0).getRoleCabinet());
        model.setViewName("consultation/consultationDetails");

        return model;
    }

    @RequestMapping(value = "/consultations/consultation/submit", method = POST)
    public String editConsultation(Consultation consultation, RedirectAttributes redirectAttributes) {

        consultationService.updateConsultation(consultation);

        redirectAttributes.addFlashAttribute("updated", "Consultation updated successfully");

        return "redirect:/consultations/consultation/" + consultation.getId();

    }

    @RequestMapping(value = "/consultations/newProduit/submit", method = POST)
    public String ajoutProduit(Produit produit, RedirectAttributes redirectAttributes, @RequestParam(name = "id") long id) {
        Consultation consultation = consultationService.findConsultation(id);
        //add produit
        Set<Produit> produits = consultation.getProduits();

        produit.setId(0);

        produitRepository.save(produit);
        produits.add(produit);
        System.out.println("hahiya consultation khdama ----------------> " + consultation.getClient().getFirstName());
        System.out.println("hahowa produit khdam  ----------------> " + produit.getDesignation() + " -- " + produit.getId() + " -- " + produit.getType());
        consultation.setProduits(produits);
        consultationRepository.save(consultation);
        redirectAttributes.addFlashAttribute("updated", "Prodect added successfully");

        return "redirect:/consultations/consultation/" + id;
    }


    @RequestMapping(value = "/consultations/newConsultation/submit", method = POST)
    public String addConsultation(Consultation consultation, RedirectAttributes redirectAttributes) {

        consultationService.createConsultation(consultation);

        redirectAttributes.addFlashAttribute("updated", "Consultation created successfully");

        return "redirect:/home";

    }


    @RequestMapping(value = "/consultations/genererFacture/{id}/{idConsultation}", method = GET)
    public String genererFacture(@PathVariable("idConsultation") long idConsultation, Model model, @PathVariable("id") long id, RedirectAttributes redirectAttributes) {

        Consultation consultation = consultationService.findConsultation(idConsultation);

        Client client = consultation.getClient();
        double montantTotal = 0.0;
        for (Produit produit : consultation.getProduits()) {
            if (produit.getDesignation().equals("Verre"))
                montantTotal = montantTotal + 2 * produit.getPrix();
            else
                montantTotal = montantTotal + produit.getPrix();
        }

        if (id == 1) {

            Facture facture = Facture.builder().client(client).montantTotal(montantTotal).consultation(consultation).etat("Payé").build();
            factureRepository.save(facture);

            redirectAttributes.addFlashAttribute("updated", "facture Updated successfully");

            return "redirect:/facturedetail?id="+facture.getId();
        }
        else  {
            Facture facture =  Facture.builder().client(client).montantTotal(montantTotal).consultation(consultation).etat("En Attente").build();
            factureRepository.save(facture);

            redirectAttributes.addFlashAttribute("updated", "facture Updated successfully");

            return "redirect:/facturedetail?id="+facture.getId();
        }


    }
}
