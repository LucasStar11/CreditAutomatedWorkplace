package com.example.CreditAutomatedWorkplace.dao;

import com.example.CreditAutomatedWorkplace.model.Client;
import com.example.CreditAutomatedWorkplace.utils.HibernateSessionFactoryUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Transactional
public class ClientDao {

    private final HibernateSessionFactoryUtil hibernateSessionFactoryUtil;

    public ClientDao(HibernateSessionFactoryUtil hibernateSessionFactoryUtil) {
        this.hibernateSessionFactoryUtil = hibernateSessionFactoryUtil;
    }

    public Client findById(Long id){
        return hibernateSessionFactoryUtil.getSessionFactory().openSession().get(Client.class, id);
    }

    public List<Client> findByFullNameAndPhoneAndPassport(String fullName, String phone, String passport) {
        List<Client> clients = hibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Client where phone like :phone and " +
                                                  "fullName like :fullname and " +
                                                  "passportData like :passport")
                .setParameter("phone","%"+phone+"%")
                .setParameter("fullname","%"+fullName+"%")
                .setParameter("passport","%"+passport+"%")
                .list();
        return clients;
    }

    public Long saveClient(Client client){
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction =  session.beginTransaction();
        session.persist(client);
        transaction.commit();
        session.close();
        return client.getId();
    }

    public List<Client> findAll() {
        List<Client> clients = (List<Client>)  hibernateSessionFactoryUtil.getSessionFactory()
                .openSession()
                .createQuery("from Client")
                .list();
        return clients;
    }

}
