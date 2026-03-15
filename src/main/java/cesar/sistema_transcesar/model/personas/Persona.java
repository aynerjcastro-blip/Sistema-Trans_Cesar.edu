package cesar.sistema_transcesar.model.personas;

import cesar.sistema_transcesar.model.interfaces.Imprimible;

public abstract class Persona implements Imprimible {
    private String cedula;
    private String nombre;

    public Persona(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public Persona(){
        
    }
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void imprimirDetalle(){
        System.out.println("Cedula:" + cedula
        + "Nombre: " + nombre);
    }
}
