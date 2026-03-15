package cesar.sistema_transcesar.edu.model;

import cesar.sistema_transcesar.model.interfaces.Calculable;
import cesar.sistema_transcesar.model.interfaces.Imprimible;

public class Ticket implements Imprimible, Calculable {

    private int idTicket;
    private Pasajero pasajero;
    private String placaVehiculo;
    private String fechaViaje;
    private double tarifaBase;

    public Ticket(int idTicket, Pasajero pasajero, String placaVehiculo, String fechaViaje, double tarifaBase) {
        this.idTicket = idTicket;
        this.pasajero = pasajero;
        this.placaVehiculo = placaVehiculo;
        this.fechaViaje = fechaViaje;
        this.tarifaBase = tarifaBase;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getFechaViaje() {
        return fechaViaje;
    }

    public void setFechaViaje(String fechaViaje) {
        this.fechaViaje = fechaViaje;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    @Override
    public double calcularTotal() {
        return pasajero.calcularDescuento(tarifaBase);
    }

    @Override
    public void imprimirDetalle() {
        System.out.println("=== Ticket ===");
        System.out.println("ID: " + idTicket);
        System.out.println("Pasajero: " + pasajero.getNombre() + " (" + pasajero.getCedula() + ")");
        System.out.println("Tipo Pasajero: " + pasajero.getTipoPasajero());
        System.out.println("Placa Vehiculo: " + placaVehiculo);
        System.out.println("Fecha Viaje: " + fechaViaje);
        System.out.println("Tarifa Base: " + tarifaBase);
        System.out.println("Total a Pagar: " + calcularTotal());
    }
}
