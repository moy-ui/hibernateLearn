package com.moy.hibernateapp;

import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Scanner;

public class HibernateEliminar {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Ingrese el id cliente a eliminar: ");
        Long id = in.nextLong();
        EntityManager em = JpaUtil.getEntityManager();

        try{

            Cliente cliente = em.find(Cliente.class, id);
            em.getTransaction().begin();
            em.remove(cliente);
            em.getTransaction().commit();
        }catch (Exception e){
            em.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
    }
}
