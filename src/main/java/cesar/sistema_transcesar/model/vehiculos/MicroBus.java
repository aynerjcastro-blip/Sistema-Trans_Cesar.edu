package cesar.sistema_transcesar.model.vehiculos;

import cesar.sistema_transcesar.model.rutas.Ruta;

public class MicroBus extends Vehiculo {
    public MicroBus(String placa, Ruta ruta, boolean disponible){
        super(placa,ruta,25,0,10000,true);
    }
}
