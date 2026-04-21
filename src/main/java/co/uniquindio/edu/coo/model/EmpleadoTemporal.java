package co.uniquindio.edu.coo.model;

import lombok.Getter;
import lombok.Setter;

// Getters and setters usando Lombok
@Getter
@Setter

public class EmpleadoTemporal extends Empleado{

    private double diasTrabajados;
    private double valorDia;

    // Constructor

    public EmpleadoTemporal(String nombre, String documento, int edad, double salarioBase,
                            double porcentajeDescuentoSalud, double porcentajeDescuentoPension,
                            Empresa ownedByEmpresa, CategoriaEmpleado categoria, TipoEmpleado tipoEmpleado,
                            double diasTrabajados, double valorDia) throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        super(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud, porcentajeDescuentoPension,
                ownedByEmpresa, categoria, tipoEmpleado);
        this.diasTrabajados = diasTrabajados;
        this.valorDia = valorDia;
    }

    //------------------------------------------IMPLEMENTACIÓN DE MÉTODOS----------------------------------------------

    @Override
    public double calcularSalarioBruto() {
        double salarioBruto = calcularPagoDiasTrabajados() + calcularBonificacion();
        return salarioBruto;
    }

    //----------------------------------------MÉTODOS PROPIOS DE LA CLASE------------------------------------------------
    public double calcularPagoDiasTrabajados(){
        double pagoDiasTrabajados = diasTrabajados * valorDia;
        return pagoDiasTrabajados;
    }

    //---------------------------------------RESUMEN DE INFORMACIÓN-----------------------------------------------------

    @Override
    public String mostrarInformacion() {
        String mensaje = super.mostrarInformacion();
        mensaje += "| dias trabajados: " + diasTrabajados + " | valor día: " + valorDia + " |";
        return mensaje;
    }
}

