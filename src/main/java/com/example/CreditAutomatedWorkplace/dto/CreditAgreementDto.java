package com.example.CreditAutomatedWorkplace.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
public class CreditAgreementDto {
    private String fullName;
    private String passportData;
    private String maritalStatus;
    private String address;
    private String phone;
    private String employmentInfo;
    private Long creditAmount;

    private String statusApplication;
    private Long approvedAmount;
    private Long creditPeriod;

    private String statusAgreement;
    private LocalDateTime lastUpdate;
}
