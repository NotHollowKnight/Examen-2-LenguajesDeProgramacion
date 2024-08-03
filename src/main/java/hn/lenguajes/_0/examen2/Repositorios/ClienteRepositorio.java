package hn.lenguajes._0.examen2.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.lenguajes._0.examen2.Modelos.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, String>{
    
}
