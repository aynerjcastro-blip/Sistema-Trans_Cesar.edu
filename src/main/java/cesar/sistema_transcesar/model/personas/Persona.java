package cesar.sistema_transcesar.model.personas;

import cesar.sistema_transcesar.model.interfaces.Imprimible;
import java.time.LocalDate;
import java.time.Period;

public abstract class Persona implements Imprimible {
    private String identificacion;
    private String nombre;
    protected LocalDate fechaNacimiento;

    public Persona(){

    }

    public Persona(String identificacion, String nombre) {
        this.identificacion = identificacion;
        this.nombre = nombre;
    }

    public Persona(String identificacion, String nombre, LocalDate fechaNacimiento) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int calcularEdad() {
        if (fechaNacimiento == null) return 0;
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public boolean esAdultoMayor() {
        return calcularEdad() >= 60;
    }

    @Override
    public String toString() {
        return identificacion + ";" + nombre;
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("Identificacion: " + identificacion 
            + " | Nombre: " + nombre
            + " | Edad: " + calcularEdad());
    }
}
