package cesar.sistema_transcesar.model.personas;

public abstract class Pasajero extends Persona {

    public Pasajero(String id, String nombre) {
        super(id, nombre);
    }

    public abstract double calcularDescuento();
}