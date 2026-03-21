package cesar.sistema_transcesar.model.vehiculos;

public class Bus extends Vehiculo {
    public Bus(String placa, String ruta, boolean disponible){
        super(placa, ruta, 45, 0, 15000, true);
    }
}
