package cesar.sistema_transcesar.model.personas;

public class PasajeroRegular extends Pasajero {

    public PasajeroRegular(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.0;
    }

    // CORRECCIÓN: no tenía imprimirDetalle() propio, heredaba el genérico
    // de Persona sin mostrar el tipo ni el descuento
    @Override
    public void imprimirDetalle() {
        System.out.println("Cedula: " + getCedula()
                + " | Nombre: " + getNombre()
                + " | Tipo: Regular"
                + " | Descuento: 0%");
    }
}