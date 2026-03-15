package cesar.sistema_transcesar.edu.model;

public class PasajeroEstudiante extends Pasajero {

    public PasajeroEstudiante(String cedula, String nombre, String telefono) {
        super(cedula, nombre, telefono, "Estudiante");
    }

    @Override
    public double calcularDescuento(double tarifa) {
        return tarifa * 0.85;
    }

    @Override
    public void imprimirDetalle() {
        super.imprimirDetalle();
        System.out.println("Descuento: 15%");
    }
}
