package com.moy.hibernateapp;

import com.moy.hibernateapp.dominio.ClienteDto;
import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {


        EntityManager em = JpaUtil.getEntityManager();
        System.out.println("=============Listar todos");
        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
        clientes.forEach(System.out::println);

        System.out.println("==========Consulta por ID");
        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
                .setParameter("id", 1L).getSingleResult();
        System.out.println(cliente);


        System.out.println(" =================Consulta solo el nombre por el id: ");
        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id =:id", String.class)
                .setParameter("id", 2L)
                .getSingleResult();
        System.out.println("nombreCliente = " + nombreCliente);

        System.out.println("Consulta por campos personalizados========");
        Object[] campos = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
                .setParameter("id", 1L)
                .getSingleResult();
        Long id =(Long) campos[0];
        String nombre = (String) campos[1];
        String apellido = (String) campos[2];
        System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " +apellido);

        System.out.println("Consulta por campos personalizados List========");
        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
                .getResultList();

        for(Object[] reg : registros){
            id =(Long) reg[0];
            nombre = (String) reg[1];
            apellido = (String) reg[2];
            System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " +apellido);
        }


        System.out.println("========== Consulta por cliente y forma pago ========");
        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
                        .getResultList();
        registros.forEach(reg -> {
            Cliente c = (Cliente) reg[0];
            String formaPago = (String) reg[1];
            System.out.println("Forma pago: " + formaPago + ", " + c);
        });


        System.out.println("========Consulta que puebla y devuelbe el objeto Cliente limitado ======");
       em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
                .getResultList().forEach(System.out::println);

        System.out.println("========Consulta que puebla y devuelbe el objeto DTO personalizado ======");
        em.createQuery("select new com.moy.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
                .getResultList().forEach(System.out::println);

        em.close();
    }
}
