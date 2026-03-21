package cesar.sistema_transcesar.model.vehiculos;

import cesar.sistema_transcesar.model.rutas.Ruta;

public class Bus extends Vehiculo {
    public Bus(String placa, Ruta ruta, boolean disponible){
        super(placa, ruta, 45, 0, 15000, disponible);
    }
}
