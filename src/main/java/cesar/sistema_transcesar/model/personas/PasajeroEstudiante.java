package cesar.sistema_transcesar.model.personas;

import cesar.sistema_transcesar.model.personas.Pasajero;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }
}