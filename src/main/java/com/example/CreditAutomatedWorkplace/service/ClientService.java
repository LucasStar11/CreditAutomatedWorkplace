package com.example.CreditAutomatedWorkplace.service;

import com.example.CreditAutomatedWorkplace.dao.ClientDao;
import com.example.CreditAutomatedWorkplace.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientDao clientDao;

    @Autowired
    public ClientService(ClientDao clientDao){
        this.clientDao = clientDao;
    }

    public List<Client> searchClients(String fullName, String phone, String passport){
        return clientDao.findByFullNameAndPhoneAndPassport(fullName,phone,passport);
    }

    public List<Client> findAllClients(){
        return clientDao.findAll();
    }

}
