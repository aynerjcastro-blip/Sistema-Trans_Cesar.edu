package cesar.sistema_transcesar.model.personas;

import cesar.sistema_transcesar.model.personas.Pasajero;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }
}