package com.projet.service;


import com.projet.model.User;

import java.util.List;

public interface UserService {

    public User findUserByEmail(String email);

    public void saveUser(User user);

    List<User> listAll();

}
