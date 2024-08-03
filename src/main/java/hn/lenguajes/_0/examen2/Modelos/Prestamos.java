package hn.lenguajes._0.examen2.Modelos;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="prestamos")
@Data
public class Prestamos {
    
    @Id
    @Column(name="codigoprestamo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoPrestamo;

    @Column(name="fechaapertura")
    private LocalDate fechaApertura;

    private double monto;

    private double cuota;

    private int plazo;

    private double interes;

    //dni FK varchar(20)
    @JsonIncludeProperties(value={"dni","nombre","apellido","telefono"})
    @ManyToOne
    @JoinColumn(name="dni")
    private Cliente cliente;

    @OneToMany(mappedBy = "prestamos", cascade = CascadeType.ALL)
    private List<Cuotas> cuotas;

}
