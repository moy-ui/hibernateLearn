package com.moy.hibernateapp.services;

import com.moy.hibernateapp.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> lister();
    Optional<Cliente> porId(Long id);
    void guardar(Cliente cliente);
    void eliminar(long id);
}
