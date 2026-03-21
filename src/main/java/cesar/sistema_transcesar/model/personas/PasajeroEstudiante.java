package cesar.sistema_transcesar.model.personas;

import java.time.LocalDate;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre,fechaNacimiento);
    }

    @Override
    public double calcularDescuento() {
        return 0.15;
    }
}