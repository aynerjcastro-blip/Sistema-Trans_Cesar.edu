package cesar.sistema_transcesar.model.reservas;

import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;
import java.time.LocalDate;

public class Reserva {
    private String codigo;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fechaCreacion;
    private LocalDate fechaViaje;
    private String estado; // Activa, Convertida, Cancelada

    public Reserva(String codigo, Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaCreacion, LocalDate fechaViaje, String estado) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fechaCreacion = fechaCreacion;
        this.fechaViaje = fechaViaje;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(LocalDate fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void imprimirDetalle(){
        System.out.println("\nCodigo: " + codigo
        + "\nPasajero: " + pasajero
        + "\nVehiculo: " + vehiculo
        + "\nFecha de Creacion: " + fechaCreacion
        + "\nFecha de Viaje: " + fechaViaje
        + "\nEstado: " + estado);
    }

    @Override
    public String toString() {
        return codigo + ";" +
                pasajero.getIdentificacion() + ";" +
                vehiculo.getPlaca() + ";" +
                fechaCreacion + ";" +
                fechaViaje + ";" +
                estado;
    }
}
