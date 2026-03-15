package cesar.sistema_transcesar.edu.model;

public class Conductor extends Persona {

    private String licencia;
    private String categoria;

    public Conductor(String id, String nombre, String licencia, String categoria) {
        super(id, nombre);
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
}