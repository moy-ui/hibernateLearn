package com.moy.hibernateapp;

import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.util.Scanner;

public class HibernatePorId {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("Ingrese el id");
        Long id = s.nextLong();
        EntityManager em = JpaUtil.getEntityManager();
        //Query query = em.createQuery("select c from Cliente c where c.id=?1", Cliente.class);
        Cliente cliente = em.find(Cliente.class, id);
        System.out.println(cliente);

        Cliente cliente2 = em.find(Cliente.class, id);
        System.out.println("cliente2 = " + cliente2);
        
//        query.setParameter(1, pago);
//        Cliente c = (Cliente) query.getSingleResult();
        em.close();
    }
}
