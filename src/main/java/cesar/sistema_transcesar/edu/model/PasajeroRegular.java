package cesar.sistema_transcesar.edu.model;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono, "Regular");
    }

    @Override
    public double calcularDescuento(double tarifa) {
        return tarifa;
    }

    @Override
    public void imprimirDetalle() {
        super.imprimirDetalle();
        System.out.println("Descuento: 0%");
    }
}
