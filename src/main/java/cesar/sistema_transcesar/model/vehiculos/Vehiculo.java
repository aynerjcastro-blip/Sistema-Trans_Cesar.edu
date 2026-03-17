package cesar.sistema_transcesar.model.vehiculos;

import cesar.sistema_transcesar.model.interfaces.Imprimible;

public abstract class Vehiculo implements Imprimible {
    private String placa;
    private int capacidadMaxima;
    private int pasajerosActuales;
    private double tarifaBase;
    private boolean disponible;

        this.placa = placa;
        this.ruta = ruta;
        this.capacidadMaxima = capacidadMaxima;
        this.pasajerosActuales = pasajerosActuales;
        this.tarifaBase = tarifaBase;
        this.disponible = disponible;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

        return ruta;
    }

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
    }
}
