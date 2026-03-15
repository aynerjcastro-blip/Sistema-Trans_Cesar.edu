package cesar.sistema_transcesar.edu.model;

import cesar.sistema_transcesar.model.personas.Persona;

public abstract class Pasajero extends Persona {

    private String telefono;
    private String tipoPasajero;

    public Pasajero(String cedula, String nombre, String telefono, String tipoPasajero) {
        super(cedula, nombre);
        this.telefono = telefono;
        this.tipoPasajero = tipoPasajero;
    }

    public abstract double calcularDescuento(double tarifa);

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTipoPasajero() {
        return tipoPasajero;
    }

    public void setTipoPasajero(String tipoPasajero) {
        this.tipoPasajero = tipoPasajero;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Pasajero ===");
        System.out.println("Cedula: " + getCedula());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Telefono: " + telefono);
        System.out.println("Tipo: " + tipoPasajero);
    }
}
