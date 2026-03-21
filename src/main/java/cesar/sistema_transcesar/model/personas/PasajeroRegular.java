package cesar.sistema_transcesar.model.personas;

import java.time.LocalDate;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre,fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }
}