package co.uniquindio.edu.coo.model;

public enum CategoriaEmpleado {
    JUNIOR("Categoría JUNIOR"), SEMI_SENIOR("Categoría SEMI SENIOR"), SENIOR("Categoría SENIOR");

    private final String descripcion;

    CategoriaEmpleado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
