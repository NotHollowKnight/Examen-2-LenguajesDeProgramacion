package hn.lenguajes._0.examen2.Modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cliente")
@Data
public class Cliente {
    
    @Id
    @Column(name="dni")
    private String dni;

    private String nombre;

    private String apellido;

    private String telefono;

    //dni PK
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value={"cliente"})
    private List<Prestamos> prestamos;
}
