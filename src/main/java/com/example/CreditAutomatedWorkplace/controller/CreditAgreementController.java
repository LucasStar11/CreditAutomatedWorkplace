package com.example.CreditAutomatedWorkplace.controller;

import com.example.CreditAutomatedWorkplace.dto.CreditAgreementDto;
import com.example.CreditAutomatedWorkplace.model.CreditAgreement;
import com.example.CreditAutomatedWorkplace.service.CreditAgreementService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreditAgreementController {

    final private CreditAgreementService creditAgreementService;

    @Autowired
    public CreditAgreementController(CreditAgreementService creditAgreementService){
        this.creditAgreementService= creditAgreementService;
    }

    @GetMapping("/all-credit-agreements")
    public ResponseEntity<List<CreditAgreement>> findAll(){
        return ResponseEntity.ok(this.creditAgreementService.findAll());
    }

    @PostMapping("/add-credit-agreements")
    public ResponseEntity<CreditAgreementDto> createLoanContract(@RequestParam Long creditApplicationId){
        try {
            CreditAgreementDto creditAgreementDto = this.creditAgreementService.createAgreementDto(creditApplicationId);
            return new ResponseEntity(creditAgreementDto, HttpStatus.CREATED);
        }
        catch(HibernateException e){
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    @PostMapping("/update-credit-agreements")
    public ResponseEntity updateLoanContract(@RequestParam Long loanContractId,
                                             @RequestParam String status){
        try {
            this.creditAgreementService.updateCreditAgreement(loanContractId,status);
            return ResponseEntity.status(202).build();
        }
        catch(HibernateException e){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }


}
