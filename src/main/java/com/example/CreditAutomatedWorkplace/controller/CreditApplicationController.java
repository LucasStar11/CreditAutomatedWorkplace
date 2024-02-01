package com.example.CreditAutomatedWorkplace.controller;

import com.example.CreditAutomatedWorkplace.dto.CreditApplicationDto;
import com.example.CreditAutomatedWorkplace.model.CreditApplication;
import com.example.CreditAutomatedWorkplace.service.CreditApplicationService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditApplicationController {
    final private CreditApplicationService creditApplicationService;

    @Autowired
    public CreditApplicationController(CreditApplicationService creditApplicationService){
        this.creditApplicationService = creditApplicationService;
    }

    @GetMapping("/all-applications")
    public ResponseEntity<List<CreditApplication>> getAllApplication(){
        return ResponseEntity.ok(this.creditApplicationService.findAllCreditApplications());
    }

    @PostMapping("/add-application")
    public ResponseEntity<CreditApplication> addApplication(@RequestBody CreditApplicationDto creditApplicationDto){
        try {
            CreditApplication creditApplication = this.creditApplicationService.considerCreditApplication(creditApplicationDto);
            return new ResponseEntity(creditApplication, HttpStatus.CREATED);
        }
        catch(HibernateException e){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
