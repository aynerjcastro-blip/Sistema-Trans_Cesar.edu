package cesar.sistema_transcesar.model.personas;

public class Conductor extends Persona {

    private String licencia;
    private String categoria;

    public Conductor(String cedula, String nombre, String licencia, String categoria) {
        super(cedula, nombre);
        this.licencia = licencia;
        this.categoria = categoria;
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
        System.out.println("Cedula: " + getCedula() + " Nombre: " + getNombre()
                + " Licencia: " + licencia + " Categoria: " + categoria);
    }
}