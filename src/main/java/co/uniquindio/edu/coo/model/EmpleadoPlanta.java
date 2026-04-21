package co.uniquindio.edu.coo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Getters and setters usando Lombok
@Getter
@Setter

public class EmpleadoPlanta extends Empleado{

    //Atributos propios de un empleado de planta
    private String cargo;
    private double horasExtras;
    private double valorHoraExtra;
    private double auxilioTransporte;

    //Constructor

    public EmpleadoPlanta(String nombre, String documento, int edad, double salarioBase,
                          double porcentajeDescuentoSalud, double porcentajeDescuentoPension, Empresa ownedByEmpresa,
                          CategoriaEmpleado categoria, TipoEmpleado tipoEmpleado, String cargo, double horasExtras,
                          double valorHoraExtra, double auxilioTransporte) throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        super(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud, porcentajeDescuentoPension,
                ownedByEmpresa, categoria, tipoEmpleado);
        this.cargo = cargo;
        this.horasExtras = horasExtras;
        this.valorHoraExtra = valorHoraExtra;
        this.auxilioTransporte = auxilioTransporte;
    }


    // ------------------------------------- IMPLEMENTACIÓN DE MÉTODOS ------------------------------------------

    @Override
    public double calcularSalarioBruto() {
        double salarioBruto = salarioBase + calcularBonificacion()+ calcularPagoHorasExtras()+ auxilioTransporte;
        return salarioBruto;
    }

    //--------------------------------------MÉTODO PROPIO -----------------------------------------------------

    // Método para calcular el valor total de las horas extras realizadas por un empleado de Planta
    public double calcularPagoHorasExtras(){
        double valorHorasExtras = horasExtras * valorHoraExtra;
        return valorHorasExtras;
    }

    //--------------------------------------RESUMEN DE INFORMACIÓN -----------------------------------------------------
    @Override
    public String mostrarInformacion() {
        String mensaje = super.mostrarInformacion();
        mensaje += "| cargo: "+cargo+
                " | horas extra: "+horasExtras+" | \n"+
                " valor hora extra: "+valorHoraExtra+" | auxilio transporte: "+auxilioTransporte+" |\n";
        return mensaje;
    }
}
