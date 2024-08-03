package hn.lenguajes._0.examen2.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.lenguajes._0.examen2.Modelos.Cliente;
import hn.lenguajes._0.examen2.Servicios.ClienteServicio;



@RestController
@RequestMapping("/api/cliente")
public class ClienteControlador {
    
    @Autowired
    ClienteServicio clienteServicio;

    @GetMapping("/obtener/todo")
    public List<Cliente> obtenerTodosCliente() {
        return this.clienteServicio.obtenerTodos();
    }

    @PostMapping("/crear")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return this.clienteServicio.crear(cliente);
    }
    
    
}
