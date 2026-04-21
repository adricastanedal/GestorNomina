import co.uniquindio.edu.coo.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import java.util.logging.Logger;



/* Clase para probar los métodos de la clase Empleado y sus subclases,
 * así como los métodos de la clase Empresa relacionados con los empleados.
 * @author: Adriana Castañeda
 * @since 2026-04
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

public class TestEmpleado {

    //Instancia para el manejo de Logs
    private static final Logger LOG = Logger.getLogger(TestEmpleado.class.getName());

    @Test
    public void VerificarSalarioBrutoEmpleadoPlanta() throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        LOG.info("Inicio del test verificarSalarioBrutoEmpleadoPlanta... ");

        double salarioBase = 1000000;
        double horasExtras = 10.5;
        double valorHoraExtra = 10000;
        double auxilioTransporte = 200000;

        Empleado e = new EmpleadoPlanta("Adriana", "1006574351", 20, salarioBase,
        4, 4, null,
                CategoriaEmpleado.JUNIOR, TipoEmpleado.PLANTA, "Desarrollador", horasExtras, valorHoraExtra,
        auxilioTransporte);

        double bonificacion = salarioBase*0.05;
        double pagoHorasExtras = horasExtras*valorHoraExtra;
        double salarioBrutoEsperado = salarioBase + bonificacion + pagoHorasExtras + auxilioTransporte;
        double salarioBrutoCalculado = e.calcularSalarioBruto();

        assertEquals(salarioBrutoEsperado,salarioBrutoCalculado);

        LOG.info("Finaliza el test verificarSalarioBrutoEmpleadoPlanta... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarSalarioBrutoEmpleadoVentas() throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        LOG.info("Inicio del test verificarSalarioBrutoEmpleadoVentas... ");

        double salarioBase = 1500000;
        double porcentajeComision = 10;
        double totalVentas = 5000000;

        Empleado e = new EmpleadoVentas("Adrian", "1193496675", 21, salarioBase,
         4, 4, null, CategoriaEmpleado.SEMI_SENIOR,
                TipoEmpleado.VENTAS, totalVentas, porcentajeComision);

        double bonificacion = salarioBase*0.10;
        double comisionVentas = totalVentas*porcentajeComision/100;

        double salarioBrutoEsperado = salarioBase + bonificacion + comisionVentas;
        double salarioBrutoActual = e.calcularSalarioBruto();

        assertEquals(salarioBrutoEsperado, salarioBrutoActual);

        LOG.info("Finaliza el test verificarSalarioBrutoEmpleadoVentas... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarSalarioNetoEmpleadoTemporal() throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        LOG.info("Inicio del test verificarSalarioBrutoEmpleadoTemporal...");
        double diasTrabajados = 10.00;
        double valorDia = 300000.00;

        Empleado e = new EmpleadoTemporal("Adriana", "1006574351", 20, 0.0,
                0.0, 0.0,null, CategoriaEmpleado.SENIOR,
                TipoEmpleado.TEMPORAL, diasTrabajados, valorDia);

        double salarioNeto = diasTrabajados * valorDia;

        assertEquals(salarioNeto, e.calcularSalarioNeto());

        LOG.info("Finalizaciónd del test verificarSalarioBrutoEmpleadoTemporal... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarBonificacionEmpleadoJunior() throws IngresoValorInvalido{
        LOG.info("Inicio del test verificarBonificacionEmpleadoJunior...");

        double salarioBase = 1000000;

        Empleado e = new EmpleadoPlanta("Adriana", "1006574351", 20, salarioBase,
                4, 4, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 10.5, 10000,
                200000);

        double bonificacionEsperada = salarioBase*0.05;
        double bonificacionCalculada = e.calcularBonificacion();

        assertEquals(bonificacionEsperada, bonificacionCalculada);

        LOG.info("Finalización del test verificarBonificacionEmpleadoJunior... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarSalarioNetoMayorQueCeroPlanta() throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        LOG.info("Inicio del test verificarSalarioNetoMayorQueCero para un empleado de Planta...");

        Empleado e1 = new EmpleadoPlanta("Adriana", "1006574351", 20,1000000,
                4, 4, null,
                CategoriaEmpleado.JUNIOR, TipoEmpleado.PLANTA, "Desarrollador", 0.0, 10000,
                200000);

        double salarioNetoCalculado = e1.calcularSalarioNeto();

        assertTrue(salarioNetoCalculado > 0);
        LOG.info("Finalización del test verificarSalarioNetoMayorQueCero... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarSalarioNetoMayorQueCeroVentas() throws IngresoValorInvalido, IngresoPorcentajeInvalido{
        LOG.info("Inicio del test verificarSalarioNetoMayorQueCero para empleado de Ventas...");

        Empleado e1 = new EmpleadoVentas("Adriana", "1006574351", 20,1000000,
                4, 4, null,
                CategoriaEmpleado.JUNIOR, TipoEmpleado.VENTAS, 0.0, 10);

        double salarioNetoCalculado = e1.calcularSalarioNeto();

        assertTrue(salarioNetoCalculado > 0);
        LOG.info("Finalización del test verificarSalarioNetoMayorQueCero... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarSalarioNetoIgualACeroTemporal() throws IngresoValorInvalido, IngresoPorcentajeInvalido{
        LOG.info("Inicio del test verificarSalarioNetoMayorQueCero para empleado Temporal...");

        Empleado e = new EmpleadoTemporal("Adriana", "1006574351", 20,0.0,
                 0.0, 0.0, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.TEMPORAL, 0, 100000);

        double salarioNetoCalculado = e.calcularSalarioNeto();

        assertFalse(salarioNetoCalculado > 0);
        LOG.info("Finalización del test verificarSalarioNetoMayorQueCero... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void verificarSalarioNetoMayorQueCeroTemporal() throws IngresoValorInvalido, IngresoPorcentajeInvalido{
        LOG.info("Inicio del test verificarSalarioNetoMayorQueCero para empleado Temporal...");

        Empleado e = new EmpleadoTemporal("Adriana", "1006574351", 20,0.0,
                0.0, 0.0, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.TEMPORAL, 1, 100000);

        double salarioNetoCalculado = e.calcularSalarioNeto();

        assertTrue(salarioNetoCalculado > 0);
        LOG.info("Finalización del test verificarSalarioNetoMayorQueCero... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void busquedaEmpleadoInexistente() throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        LOG.info("Inicio test busquedaEmpleadoInexistente...");
        String id = "40926327";
        Empresa e = new Empresa("Meraki software");
        Empleado em = new EmpleadoPlanta("Amy", id,20,1000000,
                4, 4, null,
                CategoriaEmpleado.JUNIOR, TipoEmpleado.PLANTA, "Desarrollador", 0.0, 10000,
                200000);
        Optional<Empleado> empleado = e.buscarEmpleado(id);
        assertTrue(empleado.isEmpty());
        LOG.info("Finalización del test busquedaEmpleadoInexistente... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void excepcionSalarioBaseNegativo(){
        LOG.info("Inicio del test salarioBaseNegativo...");

        assertThrows(IngresoValorInvalido.class, () -> {
            new EmpleadoPlanta("Adriana", "1006574351", 20,-1000000,
                            4, 4, null, CategoriaEmpleado.JUNIOR,
                            TipoEmpleado.PLANTA, "Desarrollador", 10.5, 10000,
                            200000);
                });

        LOG.info("Finalización del test salarioBaseNegativo... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void salarioNetoMayorASalarioBase() throws IngresoValorInvalido, IngresoPorcentajeInvalido {
        LOG.info("Inicio del test salarioNetoMayorASalarioBase para un empleado de Planta...");

        Empleado e1 = new EmpleadoPlanta("Adriana", "3006574351", 20,1000000,
                4, 4, null,
                CategoriaEmpleado.SENIOR, TipoEmpleado.PLANTA, "Desarrollador", 1, 10000,
                200000);

        assertTrue(e1.calcularSalarioNeto() > e1.getSalarioBase());
        LOG.info("Finalización del test salarioNetoMayorASalarioBase... \n"+
                "------------------------------------------------------------------");
    }

}
