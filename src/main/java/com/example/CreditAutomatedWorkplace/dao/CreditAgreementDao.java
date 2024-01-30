package com.example.CreditAutomatedWorkplace.dao;

import com.example.CreditAutomatedWorkplace.model.Client;
import com.example.CreditAutomatedWorkplace.model.CreditAgreement;
import com.example.CreditAutomatedWorkplace.model.CreditAgreement;
import com.example.CreditAutomatedWorkplace.utils.HibernateSessionFactoryUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class CreditAgreementDao {

    private final HibernateSessionFactoryUtil hibernateSessionFactoryUtil;

    public CreditAgreementDao(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        this.hibernateSessionFactoryUtil = hibernateSessionFactoryUtil;
    }

    public CreditAgreement findById(Long id){
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(CreditAgreement.class, id);
    }

    public void saveCreditAgreement(CreditAgreement CreditAgreement){
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction =  session.beginTransaction();
        session.persist(CreditAgreement);
        transaction.commit();
        session.close();
    }

    public List<CreditAgreement> findAll() {
        List<CreditAgreement> creditAgreements = (List<CreditAgreement>)  hibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from CreditAgreement")
                .list();
        return creditAgreements;
    }

    public void updateCreditAgreement(Long Id, String status){
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction =  session.beginTransaction();
        session.createQuery("update CreditAgreement set status =:status where id=:id")
                .setParameter("status",status)
                .setParameter("id",Id)
                .executeUpdate();
        transaction.commit();
        session.close();
    }

}
