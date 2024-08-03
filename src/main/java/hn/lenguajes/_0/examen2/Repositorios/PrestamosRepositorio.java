package hn.lenguajes._0.examen2.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.lenguajes._0.examen2.Modelos.Prestamos;

public interface PrestamosRepositorio extends JpaRepository<Prestamos, Long>{
    
}
