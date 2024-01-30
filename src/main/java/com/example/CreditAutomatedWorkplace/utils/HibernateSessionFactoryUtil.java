package com.example.CreditAutomatedWorkplace.utils;

import jakarta.persistence.EntityManagerFactory;
import lombok.Getter;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;



@Configuration
public class HibernateSessionFactoryUtil {

    @Getter
    private static SessionFactory sessionFactory;

    @Autowired
    public HibernateSessionFactoryUtil(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        sessionFactory = factory.unwrap(SessionFactory.class);
    }
}