package cesar.sistema_transcesar.model.personas;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String id, String nombre) {
        super(id, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString() + ";REGULAR";
    }

}