package com.moy.hibernateapp;

import com.moy.hibernateapp.entity.Cliente;
import com.moy.hibernateapp.services.ClienteService;
import com.moy.hibernateapp.services.ClienteServiceImpl;
import com.moy.hibernateapp.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class HibernateCrudService {
    public static void main(String[] args) {

        EntityManager em = JpaUtil.getEntityManager();

        ClienteService service = new ClienteServiceImpl(em);

        System.out.println("==========Listar==========");
        service.lister().forEach(System.out::println);

        System.out.println("======Obtener por id=====");
        Optional<Cliente> optionalCliente= service.porId(2L);
        if(optionalCliente.isPresent()){
            System.out.println(optionalCliente.get());
        }


        System.out.println("========Insertar cliente ========");
        Cliente cliente = new Cliente();
        cliente.setNombre("Lici");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");
        service.guardar( cliente);
        System.out.println("Cliente guardado conn exito!!!");
        service.lister().forEach(System.out::println);

        System.out.println("========= Editar cliente ======");
        Long id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            service.guardar(c);
            System.out.println("Cliente editado con exito");
        });
        service.lister().forEach(System.out::println);

        System.out.println("=========== Eliminar cliente =======");
        id = cliente.getId();
        optionalCliente = service.porId(id);
        optionalCliente.ifPresent(c -> {
            service.eliminar(c.getId());
            System.out.println("Eliminado con exito!!!");
        });


        em.close();
    }
}
