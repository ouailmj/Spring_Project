package com.projet.controller;


import com.projet.model.Client;
import com.projet.model.Consultation;
import com.projet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;


    @RequestMapping(value = "/home/client/{id}", method = GET)
    public ModelAndView detailClient(@PathVariable("id") long id) {

        int i = 0;

        ModelAndView model = new ModelAndView();

        Client client = clientService.findClient(id);

        Set<Consultation> consultations;
        consultations = client.getConsultation();

        for(Consultation c : consultations){
            i = i + c.getProduits().size();
        }

        model.addObject("id",id);
        model.addObject("client",client);
        model.addObject("consultations",consultations);
        model.addObject("produits", i);

        model.setViewName("home/detail");

        return model;
    }


    @RequestMapping(value = "/home/client/edit/{id}", method = GET)
    public ModelAndView editClient(@PathVariable("id") long id) {

        int i = 0;

        Client client = clientService.findClient(id);

        Set<Consultation> consultations =  client.getConsultation();

        for(Consultation c : consultations){
            i = i + c.getProduits().size();
        }

        ModelAndView model = new ModelAndView();
        model.addObject("id",id);
        model.addObject("client",client);
        model.addObject("consultations",consultations);
        model.setViewName("home/editProfile");
        return model;
    }

    @RequestMapping(value = "/home/profile/submit" , method = POST)
    public String editProfile(Client client, RedirectAttributes redirectAttributes){

        clientService.updateClient(client);

        redirectAttributes.addFlashAttribute("updated", "Client updated successfully");

        return "redirect:/home/client/edit/" + client.getId();

    }

    @RequestMapping(value = "/home/newClient/submit" , method = POST)
    public String addClient(Client client, RedirectAttributes redirectAttributes){

        clientService.createClient(client);

        redirectAttributes.addFlashAttribute("updated", "Client created successfully");

        return "redirect:/home";

    }

}
