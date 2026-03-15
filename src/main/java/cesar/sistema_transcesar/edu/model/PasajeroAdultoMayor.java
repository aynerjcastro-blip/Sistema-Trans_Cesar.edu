package cesar.sistema_transcesar.edu.model;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono, "AdultoMayor");
    }

    @Override
    public double calcularDescuento(double tarifa) {
        return tarifa * 0.70;
    }

    @Override
    public void imprimirDetalle() {
        super.imprimirDetalle();
        System.out.println("Descuento: 30%");
    }
}
