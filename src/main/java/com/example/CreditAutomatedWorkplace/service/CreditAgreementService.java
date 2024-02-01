package com.example.CreditAutomatedWorkplace.service;

import com.example.CreditAutomatedWorkplace.dao.ClientDao;
import com.example.CreditAutomatedWorkplace.dao.CreditAgreementDao;
import com.example.CreditAutomatedWorkplace.dao.CreditApplicationDao;
import com.example.CreditAutomatedWorkplace.dto.CreditAgreementDto;
import com.example.CreditAutomatedWorkplace.model.Client;
import com.example.CreditAutomatedWorkplace.model.CreditAgreement;
import com.example.CreditAutomatedWorkplace.model.CreditApplication;
import com.example.CreditAutomatedWorkplace.utils.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAgreementService {
    private final ClientDao clientDao;
    private final CreditAgreementDao creditAgreementDao;
    private final CreditApplicationDao creditApplicationDao;
    private final MappingUtil mappingUtils;

    @Autowired
    public CreditAgreementService(ClientDao clientDao, CreditAgreementDao creditAgreementDao,
                                  CreditApplicationDao creditApplicationDao, MappingUtil mappingUtils){
        this.clientDao = clientDao;
        this.creditAgreementDao = creditAgreementDao;
        this.creditApplicationDao = creditApplicationDao;
        this.mappingUtils = mappingUtils;
    }

    public List<CreditAgreement> findAll(){
        return creditAgreementDao.findAll();
    }
    public void updateCreditAgreement(Long id, String status){
        creditAgreementDao.updateCreditAgreementStatus(id,status);
    }

    public Long saveCreditAgreement(Long creditApplicationId){
        return creditAgreementDao.saveCreditAgreement(
                CreditAgreement.builder().creditApplication(
                        creditApplicationDao.findById(creditApplicationId))
                        .build());
    }
    public CreditAgreementDto createAgreementDto(Long creditApplicationId){
        CreditAgreement creditAgreement = creditAgreementDao.findById( saveCreditAgreement(creditApplicationId));
        CreditApplication creditApplication = creditApplicationDao.findById(creditAgreement.getCreditApplication().getId());
        Client client = clientDao.findById(creditApplication.getClient().getId());

        return mappingUtils.mapToAgreementDto(creditAgreement, creditApplication, client);
    }
}
