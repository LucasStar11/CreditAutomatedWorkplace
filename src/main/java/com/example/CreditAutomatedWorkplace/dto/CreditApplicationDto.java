package com.example.CreditAutomatedWorkplace.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder
public class CreditApplicationDto {
    private String fullName;
    private String passportData;
    private String maritalStatus;
    private String address;
    private String phone;
    private String employmentInfo;
    private Long creditAmount;
}
