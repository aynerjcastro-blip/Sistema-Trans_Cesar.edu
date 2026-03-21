package cesar.sistema_transcesar.model;

import cesar.sistema_transcesar.model.interfaces.Calculable;
import cesar.sistema_transcesar.model.interfaces.Imprimible;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;
import java.time.LocalDate;

public class Ticket implements Imprimible, Calculable {

    private int id;
    private Pasajero pasajero;
    private Vehiculo vehiculo;
    private LocalDate fecha;
    private double tarifaBase;
    private double totalPagado;


    public Ticket(int id, Pasajero pasajero, Vehiculo vehiculo, LocalDate fecha, double tarifaBase) {
        this.id = id;
        this.pasajero = pasajero;
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.tarifaBase = tarifaBase;
        this.totalPagado = calcularTotal();
    }


    public int getId() {
        return id;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public double getTotalPagado() {
        return totalPagado;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public void setTotalPagado(double totalPagado) {
        this.totalPagado = totalPagado;
    }


    @Override
    public double calcularTotal() {
        double descuento = pasajero.calcularDescuento();
        return tarifaBase - (tarifaBase * descuento);
    }

        @Override
    public String toString() {
        return id + ";" + fecha + ";" + pasajero.getIdentificacion() + ";" + vehiculo.getPlaca() + ";" + tarifaBase + ";" + totalPagado;
    }
    
    @Override   
    public void imprimirDetalle() {
        System.out.println("===== TICKET #" + id + " =====");
        System.out.println("Fecha: " + fecha);
        System.out.println("Pasajero: " + pasajero.getNombre() + " | Identificacion: " + pasajero.getIdentificacion());
        System.out.println("Vehiculo: " + vehiculo.getPlaca() + " | Ruta: " + vehiculo.getRuta());
        System.out.println("Tarifa Base: $" + tarifaBase);
        System.out.println("Descuento: " + (pasajero.calcularDescuento() * 100) + "%");
        System.out.println("Total Pagado: $" + totalPagado);
        System.out.println("=============================");
    }
}