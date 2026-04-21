package co.uniquindio.edu.coo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// Getters and setters usando Lombok
@Getter
@Setter

public abstract class Empleado {

    // Atributos

    protected String nombre;
    protected String documento;
    protected int edad;
    protected double salarioBase;
    protected double porcentajeDescuentoSalud;
    protected double porcentajeDescuentoPension;

    // Relaciones
    protected Empresa ownedByEmpresa;
    protected CategoriaEmpleado categoria;
    protected TipoEmpleado tipoEmpleado;
    protected List<ResumenPago> listaResumenPago;

    //Constructor

    public Empleado(String nombre, String documento, int edad, double salarioBase, double porcentajeDescuentoSalud,
                    double porcentajeDescuentoPension, Empresa ownedByEmpresa, CategoriaEmpleado categoria,
                    TipoEmpleado tipoEmpleado) throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        if (salarioBase < 0) {
            throw new IngresoValorInvalido("Error: El salario base debe ser mayor a 0. Valor ingresado: " + salarioBase);
        }
        if (porcentajeDescuentoSalud < 0 || porcentajeDescuentoSalud > 100) {
            throw new IngresoPorcentajeInvalido("Error: El porcentaje de descuento por salud debe estar entre 0 y 1. Valor ingresado: " + porcentajeDescuentoSalud);
        }
        if (porcentajeDescuentoPension < 0 || porcentajeDescuentoPension > 100) {
            throw new IngresoPorcentajeInvalido("Error: El porcentaje de descuento por pensión debe estar entre 0 y 1. Valor ingresado: " + porcentajeDescuentoPension);
        }
        this.nombre = nombre;
        this.documento = documento;
        this.edad = edad;
        this.salarioBase = salarioBase;
        this.porcentajeDescuentoSalud = porcentajeDescuentoSalud;
        this.porcentajeDescuentoPension = porcentajeDescuentoPension;
        this.ownedByEmpresa = ownedByEmpresa;
        this.categoria = categoria;
        this.tipoEmpleado = tipoEmpleado;
        this.listaResumenPago = new ArrayList<>();
    }

    //-------------------------------------MÈTODOS ---------------------------------------------------------------

    //Mètodo que calcula el porcentaje de bonificaciòn segùn la categorìa del empleado.
    public double calcularBonificacion() {
        double bonificacion = switch (categoria) {
            case JUNIOR -> (salarioBase * 0.05); // 5% de bonificación para empleados junior
            case SEMI_SENIOR -> (salarioBase * 0.10); // 10% de bonificación para semi senior
            case SENIOR -> (salarioBase * 0.15); // 15% de bonificación para senior
        };
        return bonificacion;
    }

    //Mètodo que calcula el total de descuentos aplicados al salario del empleado.
    public double calcularDescuentos() {
        double descuentos = salarioBase*((porcentajeDescuentoSalud/100) + (porcentajeDescuentoPension/100));
        return descuentos;
    }

    //Mètodo para calcular el salario neto de un empleado.
    public double calcularSalarioNeto() {

        double salarioNeto = calcularSalarioBruto() - calcularDescuentos();
        return salarioNeto;
    }

    //Mètodo abastracto para calcular el salario bruto del empleado.
    public abstract double calcularSalarioBruto();

    //Mètodo abstracto que genera un resumen con la informaciòn de pago de un empleado.
    public ResumenPago generarResumenPago(){
        ResumenPago resumenPago = new ResumenPago (documento, nombre, tipoEmpleado,
                calcularSalarioBruto(),
                calcularDescuentos(),
                calcularSalarioNeto());
        return resumenPago;
    }

    //Mètodo para añadir un resumen de pago a la lista.
    public void agregarResumenPago(ResumenPago resumenPago) {
        this.listaResumenPago.add(resumenPago);
    }

    //Mètodo para mostrar la informaciòn de un empleado
    public String mostrarInformacion(){
        return  " | nombre: "+ nombre +
                " | documento: "+documento +
                " | " + edad+" años |\n" +
                " | salario base: $ " + salarioBase +
                " | empresa: " + ownedByEmpresa+" |\n" +
                " | categoría: "+ categoria+"| \n";
    }
}

