package cesar.sistema_transcesar.edu.model;

import cesar.sistema_transcesar.model.personas.Persona;

public class Conductor extends Persona {

    private String telefono;
    private String licencia;
    private String categoria;

    public Conductor(String cedula, String nombre, String telefono, String licencia, String categoria) {
        super(cedula, nombre);
        this.telefono = telefono;
        this.licencia = licencia;
        this.categoria = categoria;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Conductor ===");
        System.out.println("Cedula: " + getCedula());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Telefono: " + telefono);
        System.out.println("Licencia: " + licencia);
        System.out.println("Categoria: " + categoria);
    }
}