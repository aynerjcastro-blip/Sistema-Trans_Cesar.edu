package cesar.sistema_transcesar.model.personas;

public class Conductor extends Persona {

    private String licencia;
    private String categoria; // B1, B2, C1, C2


    public Conductor(String cedula, String nombre, String licencia, String categoria) {
        super(cedula, nombre);
        this.licencia = licencia;
        this.categoria = categoria;
    }

    public String getLicencia() {
        return licencia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /*toString para escribir correctamente en Filewriter , leer ; */
    public String toString() {
        return getIdentificacion() + ";" + getNombre() + ";" + licencia + ";" + categoria;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("Identificacion: " + getIdentificacion()
                + " | Nombre: " + getNombre()
                + " | Licencia: " + licencia
                + " | Categoria: " + categoria);
    }
}