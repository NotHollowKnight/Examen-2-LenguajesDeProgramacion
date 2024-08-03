package hn.lenguajes._0.examen2.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="cuotas")
@Data
public class Cuotas {
    
    @Id
    @Column(name="codigocuota")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long codigoCuota;

    private int mes;

    private double interes;

    private double capital;

    private double saldo;

    //codigoPrestamo FK 
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="codigoprestamo")
    private Prestamos prestamos;
}
