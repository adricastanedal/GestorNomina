package co.uniquindio.edu.coo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;
// Getters and setters usando Lombok
@Getter
@Setter

public class Empresa {

    //Atributo
    private String nombre;

    //Relación
    private List<Empleado> listaEmpleados;

    //Constructor
    public Empresa(String nombre) {
        this.nombre = nombre;
        this.listaEmpleados = new ArrayList<>();
    }

    //-------------------------------------MÉTODOS-------------------------------------------------------------------

    /* Método que recorre la lista de empleados de la empresa y calcula el total de la nómina a pagar.
     * @return el valor total de la nómina a pagar a los empleados registrados en la empresa.
     */
    public double calcularNomitaTotal(){
        double nominaTotal = 0;
        for (Empleado empleado : listaEmpleados) {
            nominaTotal += empleado.calcularSalarioNeto();
        }
        return nominaTotal;
    }

    /*Método que recorre la lista de empleados de la empresa y muestra la información de cada uno de ellos.
     * @return un mensaje con la información de los empleados registrados en la empresa.
     */
    public String listarEmpleados(){
        String mensaje = "Empleados registrados en la empresa " + nombre + ":\n";
        for (Empleado empleado : listaEmpleados) {
            mensaje += empleado.mostrarInformacion() + "\n";
        }
        return mensaje;
    }

    /* Método que recibe un empleado, lo busca en la lista de empleados de la empresa y si no lo encuentra, lo registra.
     * @param e: el empleado a registrar
     * @return: mensaje indicando si el empleado fue registrado con éxito o si ya se encuentra registrado en la empresa.
     */
    public String registrarEmpleado(Empleado e){
        String resultado = "Empleado registrado con éxito.";
        Optional<Empleado> optionalEmpleado = buscarEmpleado(e.getDocumento());
        if(optionalEmpleado.isPresent()){
            resultado = "El empleado con documento " + e.getDocumento() + " ya se encuentra registrado en la empresa.";
        }else{
            listaEmpleados.add(e);
        }
        return resultado;
    }

    //----------------------------------------MÉTODOS DE BÚSQUEDA------------------------------------------------------

    /* Método que busca a un empleado en la lista de empleados de la empresa.
     * @param documento: el numero de documento del empleado a buscar
     * @return un Optional<Empleado> que contiene el empleado encontrado o un Optional vacío si
     * no se encuentra ningún empleado con el numero de documento dado
     */
    public Optional <Empleado> buscarEmpleado(String documento){
        Optional<Empleado> resultado = listaEmpleados.stream()
                .filter(empleado -> empleado.getDocumento().equals(documento))
                .findAny();
        return resultado;
    }

    /* Método que recorre la lista de empleados de la empresa y encuentra al empleado con el mayor salario bruto.
     * @return una List<Empleado> l que contiene el/los empleados con el mayor salario bruto,
     * o puede estar vacía si no hay empleados registrados en la empresa.
     */
    public List<Empleado> buscarEmpleadoMayorSalarioNeto(){
        List<Empleado> l = new ArrayList<>();
        Optional<Empleado> aux = listaEmpleados.stream()
                .max(Comparator.comparingDouble(Empleado::calcularSalarioNeto));
        if(aux.isPresent()){
            double salarioMaximo = aux.get().calcularSalarioNeto();
            l = listaEmpleados.stream().filter(e -> e.calcularSalarioNeto() == salarioMaximo)
                    .collect(Collectors.toList());
        }
        return l;
    }

    // Método para buscar empleados con salario mayor a un valor
    public List<Empleado> empleadosConSalarioMayorA(double valor){
        List<Empleado> l = new ArrayList<>();
        for (Empleado e : listaEmpleados){
            if (e.calcularSalarioNeto() > valor){
                l.add(e);
            }
        }
        return l;
    }

    // Busca los empleados temporales que han trabajado más de un número de horas dado
    public List<Empleado> empleadosTemporalesConHorasTrabajadasMayorA(double horas){
        List<Empleado> l = new ArrayList<>();
        for (Empleado e : listaEmpleados){
            if(e.getTipoEmpleado() == TipoEmpleado.TEMPORAL){
                double horasTrabajadas = ((EmpleadoTemporal) e).getDiasTrabajados()*8.0;
                if(horasTrabajadas > horas){
                    l.add(e);
                }
            }
        }
        return l;
    }

    //-----------------------------MÉTODOS PARA OBTENER RESÚMENES DE PAGO -------------------------------------------

    /* Método que recibe un empleado y devuelve la lista de resúmenes de pago asociados a ese empleado.
     * @param e: el empleado del cual se desea obtener el historial de resúmenes de pago
     * @return una List<ResumenPago> que contiene los resúmenes de pago asociados al empleado dado,
     * o una lista vacía si el empleado no tiene resúmenes de pago registrados
     */
    public List<ResumenPago> obtenerHistorialResumenPagoEmpleado(String documentoEmpleado){
        Optional<Empleado> aux = buscarEmpleado(documentoEmpleado);
        if (aux.isPresent()){
            Empleado e = aux.get();
            return e.getListaResumenPago();
        }else{
            throw new NoSuchElementException("Empleado con documento " + documentoEmpleado + " no encontrado");
        }
    }

    /* Método que recibe un empleado y genera un resumen de pago para ese empleado.
     * @param e: el empleado para el cual se generará el resumen de pago
     * @return un ResumenPago que contiene la información del pago del empleado, incluyendo su salario bruto,
     * descuentos y salario neto.
     */
    public ResumenPago generarResumenPago(String documentoEmpleado){
        Optional<Empleado> aux = buscarEmpleado(documentoEmpleado);
        if(aux.isPresent()){
            Empleado e = aux.get();
            ResumenPago resumenPago = e.generarResumenPago();
            e.agregarResumenPago(resumenPago);
            return resumenPago; // en Aplicacion se llamaría el toString del resumenPago retornado por este metodo
        }else{
            throw new NoSuchElementException("Empleado con documento " + documentoEmpleado + " no encontrado");
        }
    }
}

