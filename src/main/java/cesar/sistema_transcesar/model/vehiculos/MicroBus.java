package cesar.sistema_transcesar.model.vehiculos;

public class MicroBus extends Vehiculo {
    public MicroBus(String placa, String ruta, boolean disponible){
        super(placa,ruta,25,0,10000,disponible);
    }
}
