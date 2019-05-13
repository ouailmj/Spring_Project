package com.projet.controller;

import com.projet.model.*;
import com.projet.repository.*;
import com.projet.service.ClientService;
import com.projet.service.ConsultationService;
import com.projet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.List;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    EntrADRepository entrADRepository;

    @Autowired
    ConsultationService consultationService;

    @Autowired
    ProduitRepository produitRepository;

    @RequestMapping(value = {"/login"},method = GET)
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        model.setViewName("auth/login");
        return model;
    }


    @RequestMapping(value = {"/signup"},method = GET)
    public ModelAndView signup(){
        ModelAndView model = new ModelAndView();
        User user = new User();
        model.addObject("client", user);
        model.setViewName("auth/signup");
        return model;
    }

    @RequestMapping(value = {"/signup"},method = POST)
    public ModelAndView createClient(@Valid User user, BindingResult bindingResult){
        ModelAndView model = new ModelAndView();
        User userExist = userService.findUserByEmail(user.getEmail());
        if(userExist != null) {
            bindingResult.rejectValue("email","error.auth","Email exist deja!!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("templates/auth/signup");
        }else {
            userService.saveUser(user);
            model.addObject("msg","user a ete creer avec succes");
            model.addObject("client",new User());
            model.setViewName("auth/login");
        }
        return model;
    }

    @RequestMapping(value = {"/access_denied"},method= GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }


    @RequestMapping(value = {"/home"},method= GET)
    public ModelAndView home(@RequestParam(defaultValue = "0") int page) {
        ModelAndView model = new ModelAndView();
        Client client = new Client();
        model.addObject("clients", clientService.findAll(new PageRequest(page,10,Sort.by("id").descending()),page));
        model.addObject("currentPage",page);
        model.addObject("client",client);
        model.setViewName("home/clients");

        List <User>user =userRepository.findAll();
        model.addObject("role",user.get(0).getRoleCabinet());
        return model;
    }

    @RequestMapping(value = {"/consultations"},method= GET)
    public ModelAndView consultations(@RequestParam(defaultValue = "0") int page) {
        ModelAndView model = new ModelAndView();
        Consultation consultation=new Consultation();
        model.addObject("consultations", consultationService.findAll(new PageRequest(page,10,Sort.by("id").descending()),page));
        model.addObject("currentPage",page);
        model.setViewName("consultation/consultations");
        List <User>user =userRepository.findAll();
        model.addObject("role",user.get(0).getRoleCabinet());
        return model;
    }
    @RequestMapping(value = "/product")
    public String product(Model model){
        List<Produit> produits=produitRepository.findAll();

        List <User>user =userRepository.findAll();
        model.addAttribute("role",user.get(0).getRoleCabinet());
        System.out.println(produits.isEmpty());
        model.addAttribute("liste",produits);

System.out.println(user.get(0).getRoleCabinet());

        User u=new User();
        return"home/products";

    }
    @RequestMapping(value="/saveproduct",method= RequestMethod.POST)
    public String save(@Valid Produit p)
    {
        produitRepository.save(p);

        return "redirect:product";
    }


    @RequestMapping(value = "/saveEntreprise",method= RequestMethod.POST)
    public String saveEntreprise(@Valid EntrAD add){

        entrADRepository.save(add);


        return"redirect:Entreprise";

    }

    @RequestMapping(value = "/saveClient",method= RequestMethod.POST)
    public String saveClient(@Valid User c){
        //c.roleCabinet="USER";
        c.setRoleCabinet("USER");
        userRepository.save(c);


        return"redirect:Utilisateur";

    }
    @RequestMapping(value = "/Entreprise")
    public String Entreprise(Model model){


        List <EntrAD>add = entrADRepository.findAll();

        List <User>user =userRepository.findAll();
            model.addAttribute("role", user.get(0).getRoleCabinet());
            model.addAttribute("add", add.get(0));
            System.out.println(user.get(0).getRoleCabinet());

        return"home/Entreprise";

    }
    @RequestMapping(value = "/Utilisateur")
    public String Utilisateur(Model model){



        List <User>user =userRepository.findAll();
        model.addAttribute("e",user);

        model.addAttribute("role",user.get(0).getRoleCabinet());

        return"home/Utilisateur";

    }
}
