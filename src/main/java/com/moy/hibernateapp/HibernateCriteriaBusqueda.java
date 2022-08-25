package com.moy.hibernateapp;

import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateCriteriaBusqueda {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        System.out.println("Filtro para nombre: ");
        String nombre = in.nextLine();


        System.out.println("Filtro para apellido: ");
        String apellido = in.nextLine();

        System.out.println("Filtro para la forma de pago: ");
        String formaPago = in.nextLine();


        EntityManager em = JpaUtil.getEntityManager();

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        List<Predicate> condiones = new ArrayList<>();

        if(nombre != null & !nombre.isBlank()){
            condiones.add(cb.equal(from.get("nombre"), nombre));
        }
        if (apellido != null && !apellido.isEmpty()){
            condiones.add(cb.equal(from.get("apellido"), apellido));
        }
        if(formaPago != null && !formaPago.isEmpty()){
            condiones.add(cb.equal(from.get("formaPago"), formaPago));
        }

        query.select(from).where(cb.and(condiones.toArray(new Predicate[condiones.size()])));

        em.createQuery(query).getResultList().forEach(System.out::println);

        em.close();
    }
}
