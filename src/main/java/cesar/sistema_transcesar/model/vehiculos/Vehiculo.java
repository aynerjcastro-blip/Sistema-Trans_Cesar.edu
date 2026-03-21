package cesar.sistema_transcesar.model.vehiculos;

import cesar.sistema_transcesar.model.interfaces.Imprimible;
import cesar.sistema_transcesar.model.personas.Conductor;

public abstract class Vehiculo implements Imprimible {
    private String placa;
    private String ruta;
    private int capacidadMaxima;
    private int pasajerosActuales;
    private double tarifaBase;
    private boolean disponible;
    private Conductor conductor;

    public Vehiculo(String placa, String ruta, int capacidadMaxima, int pasajerosActuales, double tarifaBase, boolean disponible, Conductor conductor) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = pasajerosActuales;
        this.tarifaBase = tarifaBase;
        this.disponible = disponible;
        this.conductor = conductor;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getPasajerosActuales() {
        return pasajerosActuales;
    }

    public void setPasajerosActuales(int pasajerosActuales) {
        this.pasajerosActuales = pasajerosActuales;
    }

    public double getTarifaBase() {
        return tarifaBase;
    }

    public void setTarifaBase(double tarifaBase) {
        this.tarifaBase = tarifaBase;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Conductor getConductor() {
        return conductor;
    }

    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    public int getCuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

    @Override
    public void imprimirDetalle(){
        System.out.println("Placa: " + placa
        + "Ruta: " + ruta
        + "Capacidad Maxima: " + capacidadMaxima
        + "Pasajeros Actuales: " + pasajerosActuales
        + "Cupos disponibles: " + getCuposDisponibles()
        + "Tarifa Base: " + tarifaBase
        + "Disponible: " + disponible);
    }

    @Override
    public String toString() {
        return placa + ";" +
                ruta + ";" +
                capacidadMaxima + ";" +
                pasajerosActuales + ";" +
                tarifaBase + ";" +
                disponible;
    }

}
