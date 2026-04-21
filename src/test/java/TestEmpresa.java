import co.uniquindio.edu.coo.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/* Clase para probar los métodos de la clase Empleado y sus subclases,
 * así como los métodos de la clase Empresa relacionados con los empleados.
 * @author: Adriana Castañeda
 * @since 2026-04
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE)
 */

public class TestEmpresa {

    // Instancia de Logger
    private static final Logger LOG = Logger.getLogger(TestEmpresa.class.getName());

    @Test
    public void verificarListaEmpresa() throws IngresoValorInvalido, IngresoPorcentajeInvalido {

        LOG.info("Inicio del test verificarListaEmpresa... ");

        Empleado e1 = new EmpleadoPlanta("Adriana", "1006574351", 20, 1000000,
                4, 4, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 10.5, 10000,
                200000);

        Empleado e2 = new EmpleadoVentas("Adrian", "1193496675", 21, 1500000,
                4, 4, null, CategoriaEmpleado.SEMI_SENIOR,
                TipoEmpleado.VENTAS, 5000000, 10);

        Empleado e3 = new EmpleadoTemporal("Camila", "1234567890", 22, 0.0,
                4, 4, null, CategoriaEmpleado.SENIOR,
                TipoEmpleado.TEMPORAL, 10, 100000);

        Empresa e = new Empresa("Meraki software");
        e.registrarEmpleado(e1);
        e.registrarEmpleado(e2);
        e.registrarEmpleado(e3);

        List<Empleado> listaEmpleadosEsperada = new ArrayList<>();
        listaEmpleadosEsperada.add(e1);
        listaEmpleadosEsperada.add(e2);
        listaEmpleadosEsperada.add(e3);

        List<Empleado> listaEmpleadosActual = e.getListaEmpleados();

        assertIterableEquals(listaEmpleadosEsperada, listaEmpleadosActual);
        LOG.info("Finaliza el test verificarListaEmpresa... \n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void empleadosConSalarioMayorA()throws IngresoValorInvalido, IngresoPorcentajeInvalido{
        LOG.info("Inicia test empleadosConSalarioMayorA...");

        Empleado e1 = new EmpleadoVentas("Camilo", "1493496675", 21, 2000000,
                4, 4, null, CategoriaEmpleado.SENIOR,
                TipoEmpleado.VENTAS, 5000000, 10);
        Empleado e2 = new EmpleadoPlanta("Fabian", "1106574351", 20, 1000000,
                4, 4, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 10.5, 10000,
                200000);
        Empleado e3 = new EmpleadoTemporal("Camila", "1234567890", 22, 0.0,
                0, 0, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.TEMPORAL, 10, 100000);

        Empresa e = new Empresa("Meraki software");
        e.registrarEmpleado(e1);
        e.registrarEmpleado(e2);
        e.registrarEmpleado(e3);

        List<Empleado> l = new ArrayList<>();
        l.add(e1);
        l.add(e2);

        assertIterableEquals(l, e.empleadosConSalarioMayorA(1000000));

        LOG.info("Finaliza test empleadosCon SalarioMayorA...\n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void empleadosConMayorMayorSalarioNeto()throws IngresoValorInvalido, IngresoPorcentajeInvalido{
        LOG.info("Inicia test empleadosConSalarioMayorA...");

        Empleado e1 = new EmpleadoPlanta("Carlos", "4006574351", 20, 10000000,
                4, 4, null, CategoriaEmpleado.SENIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 0, 10000,
                200000);
        Empleado e2 = new EmpleadoPlanta("Santiago", "5106574351", 20, 10000000,
                4, 4, null, CategoriaEmpleado.SENIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 0, 10000,
                200000);
        Empleado e3 = new EmpleadoPlanta("Erick", "2606574351", 20, 10000000,
                4, 4, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 0, 10000,
                200000);

        Empresa e = new Empresa("Meraki software");
        e.registrarEmpleado(e1);
        e.registrarEmpleado(e2);
        e.registrarEmpleado(e3);

        List<Empleado> l = new ArrayList<>();
        l.add(e1);
        l.add(e2);

        assertIterableEquals(l,e.buscarEmpleadoMayorSalarioNeto());

        LOG.info("Finaliza test empleadosConSalarioMayorA...\n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void registrarEmpleadoConDocumentoExistente() throws IngresoValorInvalido{
        LOG.info("Inicia test registrarEmpleadoConDocumentoExistente...");
        Empleado e1 = new EmpleadoPlanta("Carlos", "4006574351", 20, 10000000,
                4, 4, null, CategoriaEmpleado.SENIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 0, 20000,
                200000);
        Empleado e2 = new EmpleadoPlanta("Andy", "4006574351", 30, 3000000,
                4, 4, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.PLANTA, "Desarrollador", 0, 10000,
                200000);
        Empresa e = new Empresa("Meraki software");
        e.registrarEmpleado(e1);
        int tamañoListaInicial = e.getListaEmpleados().size();
        e.registrarEmpleado(e2);
        int sizeListaFinal = e.getListaEmpleados().size();
        assertEquals(sizeListaFinal,sizeListaFinal);

        LOG.info("Finaliza test registrarEmpleadoConDocumentoExistente...\n"+
                "------------------------------------------------------------------");
    }

    @Test
    public void empleadosTemporalesConHorasTrabajadasMayorA() throws IngresoValorInvalido, IngresoPorcentajeInvalido{
        LOG.info("Inicia test empleadosTemporalesConHorasTrabajadasMayorA...");
        Empresa e = new Empresa("Meraki software");
        Empleado e1 = new EmpleadoTemporal("Camila", "1234567890", 22, 0.0,
                0, 0, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.TEMPORAL, 100, 100000);
        Empleado e2 = new EmpleadoTemporal("Damian", "2454567890", 22, 0.0,
                0, 0, null, CategoriaEmpleado.JUNIOR,
                TipoEmpleado.TEMPORAL, 10, 100000);
        e.registrarEmpleado(e1);
        e.registrarEmpleado(e2);

        List<Empleado> lactual = e.empleadosTemporalesConHorasTrabajadasMayorA(100);
        List<Empleado> lesperada = new ArrayList<>();
        lesperada.add(e1);

        assertIterableEquals(lesperada, lactual);
        LOG.info("Finaliza test empleadosTemporalesConHorasTrabajadasMayorA...\n"+
                "------------------------------------------------------------------");
    }

}
