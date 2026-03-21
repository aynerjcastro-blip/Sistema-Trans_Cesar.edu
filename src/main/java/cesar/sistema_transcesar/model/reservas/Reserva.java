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
}
