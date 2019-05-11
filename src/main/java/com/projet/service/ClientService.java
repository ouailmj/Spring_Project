package com.projet.service;

import com.projet.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ClientService {

    public Page<Client> findAll(PageRequest pageRequest,int page);

    public Client findClient(long id);

    public Boolean updateClient(Client client);

}
