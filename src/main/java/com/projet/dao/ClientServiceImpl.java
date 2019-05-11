package com.projet.dao;

import com.projet.model.Address;
import com.projet.model.Client;
import com.projet.repository.ClientRepository;
import com.projet.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("clientService")
public class ClientServiceImpl implements ClientService {


    @Autowired
    ClientRepository clientRepository;

    @Override
    public Page<Client> findAll(PageRequest pageRequest,int page) {
        return clientRepository.findAll(new PageRequest(page,10, Sort.by("id").descending()));
    }

    @Override
    public Client findClient(long id) {
        return clientRepository.findById(id).get();
    }

    @Override
    public Boolean updateClient(Client client) {

        try {
            Client updatedClient = this.findClient(client.getId());
            Address address = new Address();
            address.setPays(client.getAddress().getPays());
            address.setVille(client.getAddress().getVille());
            address.setRue(client.getAddress().getRue());
            address.setZipCode(client.getAddress().getZipCode());
            address.setTel(client.getAddress().getTel());
            updatedClient.setAddress(address);
            updatedClient.setFirstName(client.getFirstName());
            updatedClient.setLastName(client.getLastName());
            updatedClient.setEmail(client.getEmail());
            clientRepository.save(updatedClient);
        }catch (Exception e){
            return false;
        }
        return true;
    }

}
