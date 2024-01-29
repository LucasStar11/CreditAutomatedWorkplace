package com.example.CreditAutomatedWorkplace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "passport_data")
    private String passportData;

    @Column(name = "marital_status")
    private String maritalStatus;

    private String address;

    private String phone;

    @Column(name = "employment_info")
    private String employmentInfo;

    @Column(name = "credit_amount")
    private Long creditAmount;

}