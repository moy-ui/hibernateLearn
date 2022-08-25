package com.moy.hibernateapp;

import com.moy.hibernateapp.dominio.ClienteDto;
import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class HibernateQL {
    public static void main(String[] args) {


        EntityManager em = JpaUtil.getEntityManager();
//        System.out.println("=============Listar todos");
//        List<Cliente> clientes = em.createQuery("select c from Cliente c", Cliente.class).getResultList();
//        clientes.forEach(System.out::println);
//
//        System.out.println("==========Consulta por ID");
//        Cliente cliente = em.createQuery("select c from Cliente c where c.id=:id", Cliente.class)
//                .setParameter("id", 1L).getSingleResult();
//        System.out.println(cliente);
//
//
//        System.out.println(" =================Consulta solo el nombre por el id: ");
//        String nombreCliente = em.createQuery("select c.nombre from Cliente c where c.id =:id", String.class)
//                .setParameter("id", 2L)
//                .getSingleResult();
//        System.out.println("nombreCliente = " + nombreCliente);
//
//        System.out.println("Consulta por campos personalizados========");
//        Object[] campos = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c where c.id=:id", Object[].class)
//                .setParameter("id", 1L)
//                .getSingleResult();
//        Long id =(Long) campos[0];
//        String nombre = (String) campos[1];
//        String apellido = (String) campos[2];
//        System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " +apellido);
//
//        System.out.println("Consulta por campos personalizados List========");
//        List<Object[]> registros = em.createQuery("select c.id, c.nombre, c.apellido from Cliente c", Object[].class)
//                .getResultList();
//
//        for(Object[] reg : registros){
//            id =(Long) reg[0];
//            nombre = (String) reg[1];
//            apellido = (String) reg[2];
//            System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " +apellido);
//        }
//
//
//        System.out.println("========== Consulta por cliente y forma pago ========");
//        registros = em.createQuery("select c, c.formaPago from Cliente c", Object[].class)
//                        .getResultList();
//        registros.forEach(reg -> {
//            Cliente c = (Cliente) reg[0];
//            String formaPago = (String) reg[1];
//            System.out.println("Forma pago: " + formaPago + ", " + c);
//        });
//
//
//        System.out.println("========Consulta que puebla y devuelbe el objeto Cliente limitado ======");
//       em.createQuery("select new Cliente(c.nombre, c.apellido) from Cliente c", Cliente.class)
//                .getResultList().forEach(System.out::println);
//
//        System.out.println("========Consulta que puebla y devuelbe el objeto DTO personalizado ======");
//        em.createQuery("select new com.moy.hibernateapp.dominio.ClienteDto(c.nombre, c.apellido) from Cliente c", ClienteDto.class)
//                .getResultList().forEach(System.out::println);
//
//        System.out.println(")======Consultas con nombres de clientes =============");
//        em.createQuery("select c.nombre from Cliente c", String.class)
//                        .getResultList()
//                .forEach(System.out::println);
//
//        System.out.println("=======Consulta con nombres unicos de clietes ===========");
//
//        em.createQuery("select distinct(c.nombre) from Cliente c", String.class)
//                .getResultList()
//                .forEach(System.out::println);
//
//
//        System.out.println("=============Formas de pagos unicas");
//        em.createQuery("select distinct(c.formaPago) from Cliente c", String.class)
//                        .getResultList()
//                                .forEach(System.out::println);
//
//        System.out.println("=============Consulta de cantidad de formas de pagos unicas");
//        Long total = em.createQuery("select count(distinct(c.formaPago)) from Cliente c", Long.class)
//                .getSingleResult();
//        System.out.println("total = " + total);
//
//        System.out.println("==========Consulta con nombres apellidos concatenados =====");
////        em.createQuery("select concat(c.nombre, ' ', c.apellido) as nombre_completo from Cliente c", String.class)
////                        .getResultList()
////                                .forEach(System.out::println);
//
//        System.out.println("==========Consulta con nombres apellidos concatenados =====");
//        em.createQuery("select c.nombre || ' ' || c.apellido as nombre_completo from Cliente c", String.class)
//                .getResultList()
//                .forEach(System.out::println);
//
//        System.out.println("==========Consulta con nombres apellidos concatenados Mayusculas =====");
//        em.createQuery("select upper(concat(c.nombre,' ', c.apellido)) as nombre_completo from Cliente c", String.class)
//                .getResultList()
//                .forEach(System.out::println);
//
//        System.out.println("==========Consulta con nombres apellidos concatenados Minusculas =====");
//        em.createQuery("select lower(concat(c.nombre,' ', c.apellido)) as nombre_completo from Cliente c", String.class)
//                .getResultList()
//                .forEach(System.out::println);
//
//        System.out.println("============== Buscar por considencia ===========");
//        String param = "pe";
//        em.createQuery("select c from Cliente c where c.nombre like :parametro",Cliente.class )
//                        .setParameter("parametro", "%" + param + "%")
//                                .getResultList().forEach(System.out::println);
//
//        System.out.println("============= Consultas por rango ==========");
////        em.createQuery("select c from Cliente c where c.id between 2 and 5", Cliente.class)
////                        .getResultList().forEach(System.out::println);
//        em.createQuery("select c from Cliente c where c.nombre between 'J' and 'P'", Cliente.class)
//                .getResultList().forEach(System.out::println);
//
//        System.out.println("======== Consultas ordenas ==========");
//        em.createQuery("select c from Cliente c order by c.nombre DESC, c.apellido ASC")
//                        .getResultList().forEach(System.out::println);


        System.out.println("===========Funciones de agregación COUNT");
        Long total = em.createQuery("select count(c) from Cliente c", Long.class)
                        .getSingleResult();
        System.out.println("total = " + total);

        System.out.println("===========Funciones de agregación Obtener el minimo");
        Long minimo = em.createQuery("select min(c.id) from Cliente c", Long.class)
                        .getSingleResult();
        System.out.println("minimo = " + minimo);

        System.out.println("===========Funciones de agregación MAX");
        Long maximo = em.createQuery("select max(c.id) from Cliente c", Long.class)
                .getSingleResult();
        System.out.println("maximo = " + maximo);

        System.out.println("=========== Consulta de nombre y su longitud ==========");
        em.createQuery("select c.nombre, length(c.nombre) from Cliente c", Object[].class)
                        .getResultList().forEach( reg -> {
                            String nombre = (String) reg[0];
                            Integer largo = (Integer) reg[1];
                    System.out.println(nombre + " largo: " + largo);
                });

        System.out.println("============= Consulta con el nombre mas corto ======");
        Integer minimoNombre = em.createQuery("select min(length(c.nombre)) from Cliente c", Integer.class)
                        .getSingleResult();
        System.out.println("minimoNombre = " + minimoNombre);

        System.out.println("============= Consulta con el nombre mas Largo ======");
        Integer largoNombre = em.createQuery("select max(length(c.nombre)) from Cliente c", Integer.class)
                .getSingleResult();
        System.out.println("Nombre max = " + largoNombre);


    System.out.println("================= Consultas Resumen Funciones agregaciones count min max  avg sum ====");

    Object[] objects = em.createQuery("select min(c.id), max(c.id), sum(c.id), count(c.id), avg(length(c.nombre)) from Cliente c", Object[].class)
                    .getSingleResult();
    Long min  = (Long) objects[0];
    Long max = (Long) objects[1];
    Long sum = (Long) objects[2];
    Long count = (Long) objects[3];
    Double avg = (Double) objects[4];
        System.out.print("min: " + min + ", ");
        System.out.print("max: " + max + ", ");
        System.out.print("sum: " + sum + ", ");
        System.out.print("count: " + count + ", ");
        System.out.println("avg: " + avg);


        System.out.println("============ Consulta con su nombre mas corto y su largo ==========");
        em.createQuery("select c.nombre, length(c.nombre) from Cliente c" + " where length(c.nombre) = (select min(length(c.nombre)) from Cliente c)", Object[].class)
                        .getResultList()
                                .forEach(c -> {
                                    String nom = (String) c[0];
                                    Integer largo = (Integer) c[1];
                                    System.out.println(nom + " - " + largo);
                                });

        System.out.println("================ consulta para obtener ultimo registro ============");
        Cliente c = em.createQuery("select c from Cliente c where c.id = (select max(c.id) from Cliente c)", Cliente.class)
                        .getSingleResult();
        System.out.println("c = " + c);


        System.out.println("=============== consulta where in ==============");
        em.createQuery("select c from Cliente c where c.id in :ids", Cliente.class)
                .setParameter("ids", Arrays.asList(1L,3L,9L,5L))
                        .getResultList().forEach(System.out::println);

        em.close();
    }
}
