package com.example.CreditAutomatedWorkplace.service;

import com.example.CreditAutomatedWorkplace.dao.ClientDao;
import com.example.CreditAutomatedWorkplace.dao.CreditApplicationDao;
import com.example.CreditAutomatedWorkplace.dto.CreditApplicationDto;
import com.example.CreditAutomatedWorkplace.model.CreditApplication;
import com.example.CreditAutomatedWorkplace.utils.MappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class CreditApplicationService {

    private final CreditApplicationDao creditApplicationDao;
    private final ClientDao clientDao;
    private final MappingUtil mappingUtils;

    @Autowired
    public CreditApplicationService(CreditApplicationDao creditApplicationDao, ClientDao clientDao, MappingUtil mappingUtils){
        this.creditApplicationDao = creditApplicationDao;
        this.clientDao = clientDao;
        this.mappingUtils = mappingUtils;
    }

    public List<CreditApplication> findAllCreditApplications(){
        return creditApplicationDao.findAll();
    }

    public Long saveClient(CreditApplicationDto creditApplicationDto){
        return clientDao.saveClient(mappingUtils.mapToClient(creditApplicationDto));
    }

    public Long saveCreditApplication(CreditApplicationDto creditApplicationDto){
        Random random = new Random();
        boolean decision = random.nextBoolean();
        String status = decision ? "Одобрен" : "Не одобрен";
        int multiplier = decision ? 1:0;

        return creditApplicationDao.saveCreditApplication(
                CreditApplication.builder()
                        .client(clientDao.findById(saveClient(creditApplicationDto)))
                        .status(status)
                        .approvedAmount((long) (multiplier*creditApplicationDto.getCreditAmount()*(random.nextDouble(0.5,1))))
                        .creditPeriod((long) (multiplier*(random.nextInt(12)+1)))
                        .build()
        );
    }
    public CreditApplication considerCreditApplication(CreditApplicationDto creditApplicationDto){
        return creditApplicationDao.findById(saveCreditApplication(creditApplicationDto));
    }
}
