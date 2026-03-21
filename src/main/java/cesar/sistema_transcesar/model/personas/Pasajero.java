package cesar.sistema_transcesar.model.personas;

import java.time.LocalDate;

public abstract class Pasajero extends Persona {

    public Pasajero(String id, String nombre,LocalDate fechaNacimiento) {
        super(id, nombre);
        this.fechaNacimiento=fechaNacimiento;
    }
    
    public abstract double calcularDescuento();

        @Override
    public String toString() {
        return getIdentificacion() + ";" + getNombre() + ";" + 
            getClass().getSimpleName() + ";" + fechaNacimiento;
    }
}