package com.example.CreditAutomatedWorkplace.utils;

import com.example.CreditAutomatedWorkplace.dao.ClientDao;
import com.example.CreditAutomatedWorkplace.dto.CreditAgreementDto;
import com.example.CreditAutomatedWorkplace.dto.CreditApplicationDto;
import com.example.CreditAutomatedWorkplace.model.Client;
import com.example.CreditAutomatedWorkplace.model.CreditAgreement;
import com.example.CreditAutomatedWorkplace.model.CreditApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class MappingUtil {
    public Client mapToClient(CreditApplicationDto creditApplicationDto){
        return Client.builder()
                .fullName(creditApplicationDto.getFullName())
                .passportData(creditApplicationDto.getPassportData())
                .maritalStatus(creditApplicationDto.getMaritalStatus())
                .address(creditApplicationDto.getAddress())
                .phone(creditApplicationDto.getPhone())
                .employmentInfo(creditApplicationDto.getEmploymentInfo())
                .creditAmount(creditApplicationDto.getCreditAmount())
                .build();
    }

    public CreditAgreementDto mapToAgreementDto(CreditAgreement creditAgreement, CreditApplication creditApplication, Client client){
        return CreditAgreementDto.builder()
                .fullName(client.getFullName())
                .passportData(client.getPassportData())
                .maritalStatus(client.getMaritalStatus())
                .address(client.getAddress())
                .phone(client.getPhone())
                .employmentInfo(client.getEmploymentInfo())
                .creditAmount(client.getCreditAmount())

                .statusApplication(creditApplication.getStatus())
                .approvedAmount(creditApplication.getApprovedAmount())
                .creditPeriod(creditApplication.getCreditPeriod())

                .statusAgreement(creditAgreement.getStatus())
                .lastUpdate(creditAgreement.getLastUpdate())
                .build();
    }


}
