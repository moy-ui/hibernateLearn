package com.moy.hibernateapp;

import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Scanner;

public class HibernateSingleResultListWhere {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        EntityManager em = JpaUtil.getEntityManager();
        Query query = em.createQuery("select c from Cliente c where c.formaPago=?1", Cliente.class);
        System.out.println("Ingrese la forma de pago");
        String pago = s.next();
        query.setParameter(1, pago);
//        query.setMaxResults(1);
        query.getResultList().forEach(System.out::println);
        em.close();
    }
}
