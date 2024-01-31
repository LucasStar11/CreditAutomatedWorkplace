package com.example.CreditAutomatedWorkplace.dao;

import com.example.CreditAutomatedWorkplace.model.Client;
import com.example.CreditAutomatedWorkplace.model.CreditApplication;
import com.example.CreditAutomatedWorkplace.utils.HibernateSessionFactoryUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
public class CreditApplicationDao {

    private final HibernateSessionFactoryUtil hibernateSessionFactoryUtil;

    public CreditApplicationDao(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        this.hibernateSessionFactoryUtil = hibernateSessionFactoryUtil;
    }

    public CreditApplication findById(Long id){
        return hibernateSessionFactoryUtil.getSessionFactory().openSession().get(CreditApplication.class, id);
    }

    public Long saveCreditApplication(CreditApplication creditApplication){
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction =  session.beginTransaction();
        session.persist(creditApplication);
        transaction.commit();
        session.close();
        return creditApplication.getId();
    }

    public List<CreditApplication> findAll() {
        List<CreditApplication> creditApplications = (List<CreditApplication>)  hibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from CreditApplication")
                .list();
        return creditApplications;
    }

}
