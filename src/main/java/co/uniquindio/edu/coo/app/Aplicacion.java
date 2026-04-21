package co.uniquindio.edu.coo.app;

import co.uniquindio.edu.coo.model.*;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Aplicacion {
    public static void main (String[] args) {
        Empresa e = initEmpresa();
        initMenu(e);
    }

    //--------------------------------------MÉTODOS DE INICIALIZACIÓN-------------------------------------------------//

    // Crea e inicializa una empresa.
    public static Empresa initEmpresa() {
        Empresa e = new Empresa("Meraki Software");
        return e;
    }

    // Despliega el menú principal de la aplicación.
    public static void initMenu(Empresa e){
        String mensaje = "-------------------------------------------------------------------------\n"+
                "                            "+e.getNombre()+"                            \n"+
                "-------------------------------------------------------------------------\n"+
                "Ingrese el índice de la opción a ejecutar: \n"+


                "1. Registrar un empleado\n" +
                "2. Listar todos los empleados\n" +
                "3. Buscar empleado \n" +
                "4. Buscar empleado con mayor salario neto\n" +
                "5. Mostrar nómina total de la empresa\n" +
                "6. Mostrar resumen de pago de un empleado\n" +
                "7. Mostrar historial de resúmenes de pago de un empleado\n" +
                "8. Buscar empleados con salario neto superior a un valor\n" +
                "9. Buscar empleados temporales que han trabajado más de un número de horas dadas\n" +
                "10. Salir\n";
        try{
            int input = 0;
            do{

                input = ingresarEntero(mensaje);
                switch (input){
                    case 1: procesarRegistroEmpleado(e); break;
                    case 2: mostrarListaEmpleados(e); break;
                    case 3: buscarEmpleado(e); break;
                    case 4: buscarEmpleadoMayorSalarioNeto(e); break;
                    case 5: mostrarNominaTotal(e); break;
                    case 6: generarResumenPagoEmpleado(e); break;
                    case 7: mostrarHistorialResumenPagoEmpleado(e); break;
                    case 8: empleadosConSalarioMayorA(e); break;
                    case 9: empleadosTemporalesConHorasTrabajadasMayorA(e); break;
                    case 10: mostrarMensaje("Saliendo de la aplicación..."); break;
                    default:
                        mostrarMensaje("Opción no válida. Por favor, ingrese un número entre 1 y 10.");
                }
            }while(input != 10);

        } catch (RuntimeException ex) {
            mostrarMensaje(ex.getMessage());
        }
    }

    //----------------------------------- MÉTODOS DE BÚSQUEDA Y LISTADO ----------------------------------------------//

    // Muestra la lista completa de empleados registrados.
    public static void mostrarListaEmpleados(Empresa e){
        mostrarMensaje(e.listarEmpleados());
    }

    // Busca y muestra la información de un empleado por su número de documento.
    public static void buscarEmpleado(Empresa e){
        String documentoEmpleado = ingresarCadena("Ingrese el número de documento del empleado a buscar:");
        Optional<Empleado> optionalEmpleado = e.buscarEmpleado(documentoEmpleado);
        if(optionalEmpleado.isPresent()){
            Empleado empleado = optionalEmpleado.get();
            mostrarMensaje("Empleado encontrado:\n"+empleado.mostrarInformacion());
        }else{
            mostrarMensaje("No se encontró un empleado con el número de documento " + documentoEmpleado);
        }
    }

    // Busca y muestra el empleado o empleados con mayor salario neto.
    public static void buscarEmpleadoMayorSalarioNeto(Empresa e){
        try {
            List<Empleado> l = e.buscarEmpleadoMayorSalarioNeto();
            if(l.isEmpty()){
                mostrarMensaje("No hay empleados registrados en la empresa.");
            }else{
                String mensaje = "Empleado(s) con mayor salario neto:\n";
                for (Empleado empleado : l) {
                    mensaje += empleado.mostrarInformacion() + "\n";
                }
                mostrarMensaje(mensaje);
            }
        } catch (RuntimeException ex) {
            mostrarMensaje("Error al buscar empleado: " + ex.getMessage());
        }
    }

    // Busca los empleados con salario neto superior a un valor
    public static void empleadosConSalarioMayorA(Empresa e){
        try {
            double valor = ingresarDecimal("Ingrese el valor para buscar empleados con salario neto mayor a:");
            List<Empleado> l = e.empleadosConSalarioMayorA(valor);
            if (l.isEmpty()) {
                mostrarMensaje("No se encontraron empleados con salario neto mayor a " + valor);
            } else {
                String mensaje = "Empleados con salario neto mayor a " + valor + ":\n";
                for (Empleado empleado : l) {
                    mensaje += empleado.mostrarInformacion() + "\n";
                }
                mostrarMensaje(mensaje);
            }
        } catch (IngresoValorInvalido ex) {
            mostrarMensaje("Valor inválido: " + ex.getMessage());
        } catch (RuntimeException ex) {
            mostrarMensaje("Error al buscar empleados: " + ex.getMessage());
        }
    }

    //Busca empleados temporales que han trabajado más de un número de horas dadas
    public static void empleadosTemporalesConHorasTrabajadasMayorA(Empresa e) {
        try{
            double numeroHoras = ingresarDecimal("Ingrese el número de horas base: ");
            List<Empleado> l = e.empleadosTemporalesConHorasTrabajadasMayorA(numeroHoras);
            String mensaje="";
            if(l.isEmpty()){
                mostrarMensaje("No hay empleados temporales con un numero de horas trabajadas registradas " +
                        "mayores a la base. ");
            } else{
                for (Empleado empleado : l){
                    mensaje += empleado.mostrarInformacion() + "\n";
                }
                mostrarMensaje("Empleados temporales con horas trabajadas mayores a " + numeroHoras + ":\n" + mensaje);
            }
        } catch (IngresoValorInvalido ex) {
            mostrarMensaje("Valor inválido: " + ex.getMessage());
        }
    }

    //------------------------------------- MÉTODO NÓMINA TOTAL DE LA EMPRESA ---------------------------------------//

    // Calcula y muestra la nómina total de la empresa.
    public static void mostrarNominaTotal(Empresa e){
        try {
            double nominaTotal = e.calcularNomitaTotal();
            mostrarMensaje("La nómina total de la empresa es: $ " + nominaTotal);
        } catch (RuntimeException ex) {
            mostrarMensaje("Error al calcular nómina: " + ex.getMessage());
        }
    }

    //----------------------------------MÉTODOS RELACIONADOS CON RESUMEN DE PAGO ------------------------------------//

    // Genera y muestra el resumen de pago de un empleado específico.
    public static void generarResumenPagoEmpleado(Empresa e){
        String documentoEmpleado = ingresarCadena("Ingrese el número de documento del empleado para generar " +
                "su resumen de pago:");
        try{
            ResumenPago rp= e.generarResumenPago(documentoEmpleado);
            mostrarMensaje(rp.toString());
        }catch (NoSuchElementException ex){
            mostrarMensaje(ex.getMessage());
        }
    }

    // Muestra el historial completo de resúmenes de pago de un empleado.
    public static void mostrarHistorialResumenPagoEmpleado(Empresa e){
        String documentoEmpleado = ingresarCadena("Ingrese el documento del empleado que desea consultar su historial " +
                "de resúmenes de pago:");
        try{
            List<ResumenPago> lrp = e.obtenerHistorialResumenPagoEmpleado(documentoEmpleado);
            if (lrp.isEmpty()){
                mostrarMensaje("El empleado con documento " + documentoEmpleado + " no tiene resúmenes de pago " +
                        "registrados.");
            }else{
                String mensaje = "Historial de resúmenes de pago del empleado con documento " + documentoEmpleado
                        + ":\n";
                for (ResumenPago rp : lrp) {
                    mensaje += rp.toString() + "\n";
                }
                mostrarMensaje(mensaje);
            }
        } catch (NoSuchElementException ex){
            mostrarMensaje(ex.getMessage());
        }
    }

    //--------------------------------- MÉTODOS DE REGISTRO DE UN EMPLEADO ------------------------------------------//

    // Gestiona el flujo interactivo de registro de un nuevo empleado.
    public static void procesarRegistroEmpleado(Empresa e) {
        String mensaje = "Ingrese el índice del tipo de empleado a registrar: \n"+
                "1. Empleado de Planta\n"+
                "2. Empleado de Ventas\n"+
                "3. Empleado Temporal\n"+
                "4. Volver ";
        try {
            int inputTipoEmpleado = 0;
            do {
                inputTipoEmpleado = ingresarEntero(mensaje);
                if (inputTipoEmpleado == 4) {
                    mostrarMensaje("Volviendo al menú principal...");
                    break;
                }

                String documento = ingresarCadena("Ingrese el número de documento:");

                if (!validarDocumento(documento)) {
                    continue;
                }

                if (e.buscarEmpleado(documento).isPresent()) {
                    mostrarMensaje("Error: Ya existe un empleado registrado con el número de documento " + documento);
                    continue;
                }
                String nombre = ingresarCadena("Ingrese el nombre del empleado:");
                int edad = ingresarEntero("Ingrese la edad del empleado:");
                double salarioBase = ingresarDecimal("Ingrese el salario base:");
                if (salarioBase <= 0) {
                    mostrarMensaje("Error: El salario base debe ser mayor a 0. Registro cancelado.");
                    continue;
                }
                double porcentajeDescuentoSalud = ingresarDecimalPorcentaje("Ingrese el porcentaje de descuento " +
                        "por salud (0-4):");
                if (porcentajeDescuentoSalud > 4.0) {
                    mostrarMensaje("El porcentaje de descuento por aporte a Salud excede el tope legal vigente. " +
                            "Registro cancelado.");
                    continue;
                }
                double porcentajeDescuentoPension = ingresarDecimalPorcentaje("Ingrese el porcentaje de descuento" +
                        " por pensión (0-4):");
                if (porcentajeDescuentoPension > 4.0) {
                    mostrarMensaje("El porcentaje de descuento por aporte a Pensión excede el tope legal vigente. " +
                            "Registro cancelado.");
                    continue;
                }
                double descuentoTotal = porcentajeDescuentoSalud + porcentajeDescuentoPension;
                if (descuentoTotal > 0.08) {
                    mostrarMensaje("Error: La suma de descuentos (Salud + Pensión) no puede exceder el 8%. " +
                            "Registro cancelado.");
                    continue;
                }

                CategoriaEmpleado categoria = asignarCategoria();
                if (categoria == null) {
                    mostrarMensaje("Registro cancelado.");
                    continue;
                }

                switch (inputTipoEmpleado) {
                    case 1:
                        registrarEmpleadoPlanta(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud,
                                porcentajeDescuentoPension, e, categoria);
                        break;
                    case 2:
                        registrarEmpleadoVentas(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud,
                                porcentajeDescuentoPension, e, categoria);
                        break;
                    case 3:
                        registrarEmpleadoTemporal(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud,
                                porcentajeDescuentoPension, e, categoria);
                        break;
                    default:
                        mostrarMensaje("Opción no válida. Ingrese un número entre 1 y 4.");
                }
            } while (inputTipoEmpleado != 4);

        } catch (IngresoPorcentajeInvalido ex) {
            mostrarMensaje("Porcentaje inválido: " + ex.getMessage());
        } catch (IngresoValorInvalido ex) {
            mostrarMensaje("Valor inválido: " + ex.getMessage());
        } catch (RuntimeException ex) {
            mostrarMensaje("Error: " + ex.getMessage());
        }
    }

    // Permite al usuario seleccionar la categoría del empleado.
    public static CategoriaEmpleado asignarCategoria(){
        String mensaje = "Seleccione el índice de la categoría del empleado: \n"+
                "1. Junior\n"+
                "2. Semi Senior\n"+
                "3. Senior\n"+
                "4. volver";

        int categoriaInput = 0;
        do{
            categoriaInput = ingresarEntero(mensaje);
            switch(categoriaInput){
                case 1:
                    return CategoriaEmpleado.JUNIOR;
                case 2:
                    return CategoriaEmpleado.SEMI_SENIOR;
                case 3:
                    return CategoriaEmpleado.SENIOR;
                case 4:
                    mostrarMensaje("Regresando...");
                    return null;
                default:
                    mostrarMensaje("Opción no válida. Por favor, ingrese un número entre 1 y 4.");
            }
        }while(categoriaInput != 4);
        return null;
    }

    // Registra un empleado de planta con sus datos específicos.
    public static void registrarEmpleadoPlanta(String nombre, String documento, int edad, double salarioBase,
                                               double porcentajeDescuentoSalud, double porcentajeDescuentoPension,
                                               Empresa e, CategoriaEmpleado categoria) throws IngresoValorInvalido {
        String cargo = ingresarCadena("Ingrese el cargo del empleado de planta:");
        double horasExtras = ingresarDecimal("Ingrese el número de horas extras trabajadas:");
        double valorHoraExtra = ingresarDecimal("Ingrese el valor de la hora extra:");
        double auxilioTransporte = ingresarDecimal("Ingrese el valor del auxilio de transporte:");

        Empleado empleado = new EmpleadoPlanta(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud,
                porcentajeDescuentoPension, e, categoria, TipoEmpleado.PLANTA, cargo, horasExtras,
                valorHoraExtra, auxilioTransporte);
        mostrarMensaje(e.registrarEmpleado(empleado));
    }

    // Registra un empleado de ventas con sus datos específicos.
    public static void registrarEmpleadoVentas(String nombre, String documento, int edad, double salarioBase,
                                               double porcentajeDescuentoSalud, double porcentajeDescuentoPension,
                                               Empresa e, CategoriaEmpleado categoria) throws IngresoValorInvalido {
        double totalVentas = ingresarDecimal("Ingrese el total de ventas realizadas por el empleado: ");
        double porcentajeComision = ingresarDecimalPorcentaje("Ingrese el porcentaje de comisión por ventas " +
                "(entre 0 y 100): ");
        Empleado empleado = new EmpleadoVentas(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud,
                porcentajeDescuentoPension, e, categoria, TipoEmpleado.VENTAS, totalVentas, porcentajeComision);
        mostrarMensaje(e.registrarEmpleado(empleado));
    }

    // Registra un empleado temporal con sus datos específicos.
    public static void registrarEmpleadoTemporal(String nombre, String documento, int edad, double salarioBase,
                                                 double porcentajeDescuentoSalud, double porcentajeDescuentoPension,
                                                 Empresa e, CategoriaEmpleado categoria) throws IngresoValorInvalido {
        double diasTrabajados = ingresarDecimal("Ingrese el número de días trabajados por el empleado temporal: ");
        double valorDia = ingresarDecimal("Ingrese el valor del día de trabajo para el empleado temporal: ");
        Empleado empleado = new EmpleadoTemporal(nombre, documento, edad, salarioBase, porcentajeDescuentoSalud,
                porcentajeDescuentoPension, e, categoria, TipoEmpleado.TEMPORAL, diasTrabajados, valorDia);
        mostrarMensaje(e.registrarEmpleado(empleado));
    }


    //------------------------------------MÉTODOS DE UTILIDAD-------------------------------------------

    // Valida que el documento tenga el formato correcto de cédula colombiana.
    public static boolean validarDocumento(String documento) {
        if (documento == null || !documento.matches("\\d{7,10}")) {
            mostrarMensaje("Error: El documento debe contener entre 7 y 10 dígitos. Por favor, intente nuevamente.");
            return false;
        }
        return true;
    }

    // Muestra un mensaje en una ventana de diálogo.
    public static void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    // Solicita al usuario el ingreso de un número entero positivo con máximo 3 intentos.
    public static int ingresarEntero(String mensaje){
        int intentos = 3;
        do {
            String dato = ingresarCadena(mensaje);
            if (dato == null) {
                throw new RuntimeException("Operación cancelada por el usuario.");
            }
            if (dato.matches("\\d+") && Integer.parseInt(dato) > 0) {
                return Integer.parseInt(dato);
            } else {
                intentos--;
                mostrarMensaje("Error: Ingrese un número entero positivo. Intentos restantes: " + (intentos));
            }
        } while (intentos >0);
        throw new RuntimeException("Número de intentos agotados.");
    }

    // Solicita al usuario el ingreso de un porcentaje (0-100) con máximo 3 intentos.
    public static double ingresarDecimalPorcentaje(String mensaje) {
        int intentos = 3;
        do{
            String dato = ingresarCadena(mensaje);
            if(dato==null){
                throw new RuntimeException("Operación cancelada por el usuario.");
            }
            if(dato.matches("\\d+(\\.\\d+)?")){
                double num = Double.parseDouble(dato);
                if (num>=0.0 && num<=100.0){
                    return num/100;
                }else{
                    throw new IngresoPorcentajeInvalido("Error: Ingrese un número decimal positivo entre 0 y 100.");
                }
            } else {
                intentos--;
                mostrarMensaje("Error: Ingrese un número decimal válido. Intentos restantes: " + (intentos));
            }
        } while(intentos>0);
        throw new RuntimeException("Número de intentos agotados.");
    }

    // Solicita al usuario el ingreso de un número decimal positivo con máximo 3 intentos.
    public static double ingresarDecimal(String mensaje) throws IngresoValorInvalido {
        int intentos = 3;
        do {
            String dato = ingresarCadena(mensaje);
            if (dato == null) {
                throw new RuntimeException("Operación cancelada por el usuario.");
            }
            if (dato.matches("\\d+(\\.\\d+)?")) {
                return Double.parseDouble(dato);
            } else {
                intentos--;
                mostrarMensaje("Error: Ingrese un número decimal válido. Intentos restantes: " + (intentos));
            }
        } while (intentos >0);
        throw new IngresoValorInvalido("Número de intentos agotados.");
    }

    // Solicita al usuario el ingreso de una cadena de texto mediante un cuadro de diálogo.
    public static String ingresarCadena(String mensaje){
        return JOptionPane.showInputDialog(null, mensaje);
    }
}
