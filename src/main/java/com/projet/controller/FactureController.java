package com.projet.controller;
import com.projet.model.Facture;
import com.projet.model.User;
import com.projet.repository.FactureRepository;
import com.projet.repository.UserRepository;
import com.projet.service.ClientService;
import com.projet.service.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class FactureController {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientService clientService;


    @Autowired
    FactureRepository factureRepository;

    @Autowired
    FactureService factureService;

    @RequestMapping(value = {"/facture"}, method = GET)
    public String facture(@RequestParam(defaultValue = "0") int page, Model model) {
        model.addAttribute("factures", factureService.findAll(new PageRequest(page, 10, Sort.by("id").descending()), page));
        model.addAttribute("currentPage", page);
        List<User> user =userRepository.findAll();
        model.addAttribute("role",user.get(0).getRoleCabinet());
        return "facture/facture";
    }

    @RequestMapping(value = {"/facturedetail"}, method = GET)
    public String factureDetail(@RequestParam(name = "id") Long id, Model model) {
        Facture facture = factureRepository.getOne(id);
        model.addAttribute("facture", facture);
        List <User>user =userRepository.findAll();
        model.addAttribute("role",user.get(0).getRoleCabinet());
        return "facture/detail";
    }


}