package cesar.sistema_transcesar.model.personas;

public class PasajeroAdultoMayor extends Pasajero {

    public PasajeroAdultoMayor(String cedula, String nombre) {
        super(cedula, nombre);
    }

    @Override
    public double calcularDescuento() {
        return 0.30;
    }

    // CORRECCIÓN: no tenía imprimirDetalle() propio, heredaba el genérico
    // de Persona sin mostrar el tipo ni el descuento
    @Override
    public void imprimirDetalle() {
        System.out.println("Cedula: " + getCedula()
                + " | Nombre: " + getNombre()
                + " | Tipo: Adulto Mayor"
                + " | Descuento: 30%");
    }
}