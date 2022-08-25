package com.moy.hibernateapp;

import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;

public class HibernateCriteria {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();


        CriteriaBuilder criteria = em.getCriteriaBuilder();
        CriteriaQuery<Cliente> query = criteria.createQuery(Cliente.class);
        Root<Cliente> from = query.from(Cliente.class);

        query.select(from);
        em.createQuery(query).getResultList().forEach(System.out::println);

        System.out.println("========== Listar con where equals");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreParam = criteria.parameter(String.class, "nombre");
        query.select(from).where(criteria.equal(from.get("nombre"), nombreParam));
        em.createQuery(query)
                .setParameter("nombre", "pepa")
                .getResultList().forEach(System.out::println);

        System.out.println("============= usando where  like para buscar cliente por nombre");
        query= criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<String> nombreLike = criteria.parameter(String.class, "nombreLike");
        query.select(from).where(criteria.like(criteria.upper(from.get("nombre")), criteria.upper(nombreLike)));
        em.createQuery(query)
                .setParameter("nombreLike", "%pe%")
                .getResultList().forEach(System.out::println);

        System.out.println("============== usando between para rangos");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.between(from.get("id"), 2L, 6L));
        em.createQuery(query).getResultList().forEach(System.out::println);

        System.out.println("============= Usando where in ========");
        query = criteria.createQuery(Cliente.class);
        from =query.from(Cliente.class);
        query.select(from).where(from.get("nombre").in("Andres", "Juan", "pepe"));
        em.createQuery(query).getResultList().forEach(System.out::println);


        System.out.println("================ Usando mayor o >=");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.ge(from.get("id"), 3L));
        em.createQuery(query).getResultList().forEach(System.out::println);


        System.out.println("================ Usando mayor o >=");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).where(criteria.gt(criteria.length(from.get("nombre")), 5L));
        em.createQuery(query).getResultList().forEach(System.out::println);


        System.out.println("============= consulta con los predicados conjuncion and y disyuncion or");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        Predicate porNombre= criteria.equal(from.get("nombre"), "pepa");
        Predicate formaPago = criteria.equal(from.get("formaPago"), "paypal");
        query.select(from).where(criteria.and(porNombre, formaPago));
        em.createQuery(query).getResultList().forEach(System.out::println);


        System.out.println("============== Usando con order by asc and desc");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        query.select(from).orderBy(criteria.desc(from.get("nombre")), criteria.desc(from.get("apellido")));
        em.createQuery(query).getResultList().forEach(System.out::println);

        System.out.println("============== consulta por id");
        query = criteria.createQuery(Cliente.class);
        from = query.from(Cliente.class);
        ParameterExpression<Long> idParam = criteria.parameter(Long.class, "id");
        query.select(from).where(criteria.equal(from.get("id"), idParam));
        Cliente c = em.createQuery(query)
                        .setParameter("id", 1L)
                                .getSingleResult();
        System.out.println("c = " + c);

        System.out.println("========== Usando distinct");
        CriteriaQuery<String> queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(from.get("nombre")).distinct(true);
        em.createQuery(queryString).getResultList().forEach(System.out::println);

        System.out.println("===== usando concat");
        queryString = criteria.createQuery(String.class);
        from = queryString.from(Cliente.class);
        queryString.select(criteria.concat(criteria.concat( from.get("nombre"), " "), from.get("apellido")));
        em.createQuery(queryString).getResultList().forEach(System.out::println);


        System.out.println("============ Usando multiselect ");
        CriteriaQuery<Object[]> queryObj = criteria.createQuery(Object[].class);
        from = queryObj.from(Cliente.class);
        queryObj.multiselect(from.get("id"), from.get("nombre"), from.get("apellido"));
        em.createQuery(queryObj).getResultList().forEach(o -> {
            Long id = (Long) o[0];
            String nombre = (String) o[1];
            String apellido = (String) o[2];
            System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " + apellido);
        });

        System.out.println("============ Usando multiselect  usando where ");
        queryObj = criteria.createQuery(Object[].class);
        from = queryObj.from(Cliente.class);
        queryObj.multiselect(from.get("id"), from.get("nombre"), from.get("apellido")).where(criteria.like(from.get("nombre"), "%pe%"));
        em.createQuery(queryObj).getResultList().forEach(o -> {
            Long id = (Long) o[0];
            String nombre = (String) o[1];
            String apellido = (String) o[2];
            System.out.println("id: " + id + ", nombre: " + nombre + ", apellido: " + apellido);
        });

        System.out.println("================ Usando count API CRITERIA");
        CriteriaQuery<Long> queryLong = criteria.createQuery(Long.class);
        from =queryLong.from(Cliente.class);
        queryLong.select(criteria.count(from.get("id")));
        Long count = em.createQuery(queryLong).getSingleResult();
        System.out.println("count = " + count);

        System.out.println("=============== usando function sumar ");
        queryLong = criteria.createQuery(Long.class);
        from= queryLong.from(Cliente.class);
        queryLong.select(criteria.sum(from.get("id")));
        Long sum = em.createQuery(queryLong).getSingleResult();
        System.out.println("sum = " + sum);

        System.out.println("=============== usando function max ");
        queryLong = criteria.createQuery(Long.class);
        from= queryLong.from(Cliente.class);
        queryLong.select(criteria.max(from.get("id")));
        Long max = em.createQuery(queryLong).getSingleResult();
        System.out.println("Max = " + max);

        System.out.println("=============== usando function min ");
        queryLong = criteria.createQuery(Long.class);
        from= queryLong.from(Cliente.class);
        queryLong.select(criteria.min(from.get("id")));
        Long min = em.createQuery(queryLong).getSingleResult();
        System.out.println("min = " + min);


        System.out.println("======== varias funciones de agregacion en una sola consulta");
        queryObj = criteria.createQuery(Object[].class);
        from= queryObj.from(Cliente.class);
        queryObj.multiselect(criteria.count(from.get("id")),
                criteria.sum(from.get("id")),
                        criteria.max(from.get("id")),
                                criteria.min(from.get("id")));
        Object[] regitro = em.createQuery(queryObj).getSingleResult();

        count =(Long) regitro[0];
        sum = (Long) regitro[1];
        max =(Long) regitro[2];
        min =(Long) regitro[3];
        System.out.println("count: " + count + ", sum: " + sum + ", max:" + max + ", min: " + min);






        em.close();
    }
}
