package hn.lenguajes._0.examen2.Servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.lenguajes._0.examen2.Modelos.Cliente;
import hn.lenguajes._0.examen2.Modelos.Cuotas;
import hn.lenguajes._0.examen2.Modelos.Prestamos;
import hn.lenguajes._0.examen2.Repositorios.ClienteRepositorio;
import hn.lenguajes._0.examen2.Repositorios.CuotasRepositorio;
import hn.lenguajes._0.examen2.Repositorios.PrestamosRepositorio;

@Service
public class ClienteServicio {
    
    @Autowired
    ClienteRepositorio clienteRepositorio;

    @Autowired
    PrestamosRepositorio prestamosRepositorio;

    @Autowired
    CuotasRepositorio cuotasRepositorio;

    public List<Cliente> obtenerTodos(){
        return this.clienteRepositorio.findAll();
    }

    public Cliente crear(Cliente nvoCliente){

        if(!this.clienteRepositorio.existsById(nvoCliente.getDni())){

            if(nvoCliente.getPrestamos()!=null){
                List<Prestamos> prestamoGuardar = nvoCliente.getPrestamos();
                for(Prestamos nvoPrestamo : prestamoGuardar){
                    nvoPrestamo.setCliente(nvoCliente);
                }
            }


            List<Prestamos> prestamoCuota = nvoCliente.getPrestamos();
            for(Prestamos prestamoCuotaCalcular : prestamoCuota){
                /**
                 * (monto * interes/12) / (1 - (1 + interes/12)^plazo*12)
                 * 
                 * monto*(interes/12)*(1+(interes/12))^plazo*12
                 * --------------------------------------------
                 * (1+(interes/12))^plazo+12-1
                 */

                double monto = prestamoCuotaCalcular.getMonto();
                double interesCalcular = prestamoCuotaCalcular.getInteres();
                int plazo = prestamoCuotaCalcular.getPlazo();

                double interesDivision = interesCalcular/12;

                double potenciaResultado = Math.pow((1+(interesDivision)), plazo*12);
                //cuota = (monto*interesDivision)/(1-(potenciaResultado));
                double numerador = monto*(interesDivision)*potenciaResultado;
                double denominador = Math.pow((1+(interesDivision)), plazo*12) - 1;
                double cuota = numerador/denominador;

                prestamoCuotaCalcular.setCuota(cuota);

                prestamoCuotaCalcular.setCliente(nvoCliente);

                //Tabla cuotas
                    
                    /* 
                    double potenciaResultado = Math.pow((1+(interesDivision)), plazo*12);
                    double numerador = monto*(interesDivision)*potenciaResultado;
                    double denominador = Math.pow((1+(interesDivision)), plazo*12) - 1;
                    cuota = numerador/denominador; */
                    
            }

            this.clienteRepositorio.save(nvoCliente);

            List<Prestamos> prestamoCuotaManipular = nvoCliente.getPrestamos();
            for(Prestamos prestamoCuotaCalcular : prestamoCuotaManipular){
                    int mes = 0;
                    double monto = prestamoCuotaCalcular.getMonto();
                    while(mes!=1){
                        Cuotas nvaCuota = new Cuotas();
                        nvaCuota.setPrestamos(prestamoCuotaCalcular);

                        /*if(mes == 0){
                            nvaCuota.setMes(mes);
                            nvaCuota.setInteres(0);
                            nvaCuota.setCapital(0);
                            nvaCuota.setSaldo(monto);
                        }else{
                            double interes = monto*interesDivision;
                            double capital = cuota-interes;
                            double saldo = monto-capital; //monto | saldo

                            nvaCuota.setMes(mes);
                            nvaCuota.setInteres(interes);
                            nvaCuota.setCapital(capital);
                            nvaCuota.setSaldo(saldo);

                        }*/
                        nvaCuota.setMes(mes);
                            nvaCuota.setInteres(0);
                            nvaCuota.setCapital(0);
                            nvaCuota.setSaldo(monto);

                        this.cuotasRepositorio.save(nvaCuota);

                        mes++;

                    }

            return this.clienteRepositorio.findById(nvoCliente.getDni()).get();

        }
    }

        return null;
    }
}
