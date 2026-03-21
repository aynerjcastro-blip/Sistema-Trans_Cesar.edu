package cesar.sistema_transcesar.model.vehiculos;

import cesar.sistema_transcesar.model.interfaces.Imprimible;
import cesar.sistema_transcesar.model.rutas.Ruta;

public abstract class Vehiculo implements Imprimible {
    private String placa;
    private Ruta ruta;
    private int capacidadMaxima;
    private int pasajerosActuales;
    private double tarifaBase;
    private boolean disponible;

    public Vehiculo(String placa, Ruta ruta, int capacidadMaxima, int pasajerosActuales, double tarifaBase, boolean disponible) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = pasajerosActuales;
        this.tarifaBase = tarifaBase;
        this.disponible = disponible;
    }
    //Sobrcarga de construtores
    public Vehiculo(String placa, Ruta ruta, int capacidadMaxima, int  pasajerosActuales, double tarifaBase) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = pasajerosActuales; 
        this.tarifaBase = tarifaBase;
    
    }

    public Vehiculo(String placa, Ruta ruta, int capacidadMaxima, int pasajerosActuales) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = pasajerosActuales;
    }

    public Vehiculo(String placa, Ruta ruta, int capacidadMaxima) {
        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
    }

    public Vehiculo(String placa, Ruta ruta) {
        this.placa = placa;
        this.ruta = ruta;
    }

    public Vehiculo(String placa, Ruta ruta, boolean disponible) {
        this.placa = placa;
        this.ruta = ruta;
        this.disponible = disponible;
    }
    
    
    public Vehiculo(String placa) {
        this.placa = placa;
    }
    

    public Vehiculo(){
        
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
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

    public int getCuposDisponibles() {
        return capacidadMaxima - pasajerosActuales;
    }

    @Override
    public void imprimirDetalle(){
        System.out.println("\nPlaca: " + placa
                + "\nRuta: " + ruta
                + "\nCapacidad Maxima: " + capacidadMaxima
                + "\nPasajeros Actuales: " + pasajerosActuales
                + "\nCupos disponibles: " + getCuposDisponibles()
                + "\nTarifa Base: " + tarifaBase
                + "\nDisponible: " + disponible);
    }

    @Override
    public String toString() {
        return placa + ";" +
                ruta.getCodigo() + ";" +
                getClass().getSimpleName() + ";" +
                disponible;
    }
}
