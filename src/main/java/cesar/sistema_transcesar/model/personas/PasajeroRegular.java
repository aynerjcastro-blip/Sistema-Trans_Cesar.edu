package cesar.sistema_transcesar.model.personas;

import cesar.sistema_transcesar.model.personas.Pasajero;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }
}