package cesar.sistema_transcesar.model.personas;

import java.time.LocalDate;
import java.time.Period;

public abstract class Pasajero extends Persona {

    private LocalDate fechaNacimiento;  

    public Pasajero(String id, String nombre, LocalDate fechaNacimiento) {
        super(id, nombre);
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { 
        this.fechaNacimiento = fechaNacimiento; 
    }

    public int calcularEdad() {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public abstract double calcularDescuento();

    @Override
    public String toString() {
        return getIdentificacion() + "," + getNombre() + "," +
               getClass().getSimpleName() + "," + fechaNacimiento;
    }
}