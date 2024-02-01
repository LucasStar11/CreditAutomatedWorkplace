package com.example.CreditAutomatedWorkplace.controller;

import com.example.CreditAutomatedWorkplace.model.Client;
import com.example.CreditAutomatedWorkplace.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClientController {
    final private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService1){
        this.clientService = clientService1;
    }

    @GetMapping("/client-search")
    public ResponseEntity<List<Client>> searchClient(@RequestParam(required = false) String fullName,
                                               @RequestParam(required = false) String phone,
                                               @RequestParam(required = false) String passport){
        List<Client> clients = this.clientService.searchClients(fullName,phone,passport);
        if(clients.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return ResponseEntity.ok(clients);
    }

    @GetMapping("/all-clients")
    public ResponseEntity<List<Client>> findAllClient(){
        return ResponseEntity.ok(this.clientService.findAllClients());
    }
}
