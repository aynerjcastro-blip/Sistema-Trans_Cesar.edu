package cesar.sistema_transcesar.edu.model.personas;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }
}