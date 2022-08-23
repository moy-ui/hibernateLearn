package com.moy.hibernateapp;

import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class HibernateListar {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();
        em.createQuery("SELECT c FROM Cliente c").getResultList()
                .forEach(System.out::println);
        em.close();
    }
}
