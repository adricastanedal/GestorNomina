package co.uniquindio.edu.coo.model;

public enum TipoEmpleado {
    PLANTA("Empleado de planta"), VENTAS("Empleado de ventas"), TEMPORAL("Empleado temporal");

    private final String description;

    TipoEmpleado(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

