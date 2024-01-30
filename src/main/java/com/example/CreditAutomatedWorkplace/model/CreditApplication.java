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
@Table(name = "credit_applications")
public class CreditApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @Column(name = "approved_amount")
    private Long approvedAmount;
    @Column(name = "credit_period")
    private Long creditPeriod;
}