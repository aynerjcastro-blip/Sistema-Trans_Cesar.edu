package cesar.sistema_transcesar.model.vehiculos;

import cesar.sistema_transcesar.model.rutas.Ruta;

public class Buseta extends Vehiculo {
    public Buseta(String placa, Ruta ruta, boolean disponible){
        super(placa,ruta,19,0,8000,true);
    }
}
