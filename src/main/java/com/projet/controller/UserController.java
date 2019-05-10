package com.projet.controller;

import com.projet.model.User;
import com.projet.repository.UserRepository;
import com.projet.service.ClientService;
import com.projet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientService clientService;

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
        model.addObject("clients", clientService.findAll(new PageRequest(page,10,Sort.by("id").descending()),page));
        model.addObject("currentPage",page);
        model.setViewName("home/clients");
        return model;
    }

}
