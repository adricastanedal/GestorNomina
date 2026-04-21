package co.uniquindio.edu.coo.model;

import lombok.Getter;
import lombok.Setter;

// Getters and setters usando Lombok
@Getter
@Setter

public class EmpleadoVentas extends Empleado{

    // atributos propios
    private double totalVentas;
    private double porcentajeComision;

    //Constructor

    public EmpleadoVentas(String nombre, String documento, int edad, double salarioBase,
                          double porcentajeDescuentoSalud, double porcentajeDescuentoPension,
                          Empresa ownedByEmpresa, CategoriaEmpleado categoria, TipoEmpleado tipoEmpleado,
                          double totalVentas, double porcentajeComision) throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        super(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud, porcentajeDescuentoPension,
                ownedByEmpresa, categoria, tipoEmpleado);
        this.totalVentas = totalVentas;
        this.porcentajeComision = porcentajeComision;
    }

    //------------------------------------IMPLEMENTACIÓN DE MÉTODOS------------------------------------------------

    @Override
    public double calcularSalarioBruto() {
        double salarioBruto = salarioBase + calcularBonificacion() + calcularComisionVentas();
        return salarioBruto;
    }

    //------------------------------------ MÉTODO PROPIO DE LA CLASE ------------------------------------------------

    public double calcularComisionVentas(){
        double comisionVentas = totalVentas * porcentajeComision/100;
        return comisionVentas;
    }

    //------------------------------------RESUMEN DE INFORMACIÓN ----------------------------------------------------

    @Override
    public String mostrarInformacion() {
        String mensaje = super.mostrarInformacion();
        mensaje += "| Total Ventas: " + totalVentas + " | Porcentaje Comisión: " + porcentajeComision + " % |";
        return mensaje;
    }
}

