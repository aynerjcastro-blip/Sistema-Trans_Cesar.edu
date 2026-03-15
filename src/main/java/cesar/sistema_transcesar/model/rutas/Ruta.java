package cesar.sistema_transcesar.model.personas;

public class Ruta {
    private String codigo;
    private String origen;
    private String destino;
    private double distanciaKm;
    private int tiempoEstimadoMin;


    public Ruta(String codigo, String origen, String destino, double distanciaKm, int tiempoEstimadoMin) {
        this.codigo = codigo;
        this.origen = origen;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public int getTiempoEstimadoMin() {
        return tiempoEstimadoMin;
    }

    public void setTiempoEstimadoMin(int tiempoEstimadoMin) {
        this.tiempoEstimadoMin = tiempoEstimadoMin;
    }

    @Override
    public String toString() {
        return "\nRuta..."
                + "\nCodigo: " + codigo
                + "\nOrigen: " + origen
                + "\nDestino: " + destino
                + "\nDistancia (km): " + distanciaKm
                + "\nTiempo Estimado (min): " + tiempoEstimadoMin;
    }
}