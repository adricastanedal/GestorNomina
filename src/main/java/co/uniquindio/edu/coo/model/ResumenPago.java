package co.uniquindio.edu.coo.model;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResumenPago(
        String documento,
        String nombre,
        TipoEmpleado tipoEmpleado,
        double salarioBruto,
        double descuentos,
        double salarioNeto,
        LocalDate fechaPago,
        LocalTime horaPago
) {
    // Se crea un constructor para asignar automáticamente la fecha y hora de generación del resumen de pago.
    public ResumenPago (String documento, String nombre, TipoEmpleado tipoEmpleado, double salarioBruto,
                        double descuentos, double salarioNeto){
        this(documento, nombre, tipoEmpleado, salarioBruto, descuentos, salarioNeto,
                LocalDate.now(), LocalTime.now());
    }

    //Se sobreescribe toString por presentación.
    @java.lang.Override
    public java.lang.String toString() {
        return "------------------------ ResumenPago ------------------------\n" +
                "| fecha de pago: "+fechaPago+" | hora: "+horaPago+" |\n"+
                "| documento: " + documento +" "+
                "| nombre: " + nombre +"\n"+
                "| " + tipoEmpleado +" "+
                "| salario bruto: $" + salarioBruto +"\n"+
                "| total deducciones: $ " + descuentos +
                "| salario neto: $ " + salarioNeto + "\n";
    }
}

