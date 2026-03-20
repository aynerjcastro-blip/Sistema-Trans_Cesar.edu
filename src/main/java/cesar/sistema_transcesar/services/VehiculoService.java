package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.dao.VehiculoDAO;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;
import cesar.sistema_transcesar.model.vehiculos.Buseta;
import cesar.sistema_transcesar.model.vehiculos.MicroBus;
import cesar.sistema_transcesar.model.personas.Conductor;
import cesar.sistema_transcesar.model.vehiculos.Bus;
import java.util.List;

public class VehiculoService {

    private  List<Vehiculo> vehiculos;
    private  VehiculoDAO dao;

    public VehiculoService() {
        this.dao = new VehiculoDAO();
        this.vehiculos = dao.cargar();
    }

    public void registrar(int tipo, String placa, String ruta, boolean disponible) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                throw new IllegalArgumentException("Ya existe un vehiculo con la placa: " + placa);
            }
        }
        Vehiculo nuevo;
        switch (tipo) {
            case 1: nuevo = new Buseta(placa, ruta, disponible);   break;
            case 2: nuevo = new MicroBus(placa, ruta, disponible); break;
            case 3: nuevo = new Bus(placa, ruta, disponible);      break;
            default: throw new IllegalArgumentException("Tipo de vehiculo no valido.");
        }
        vehiculos.add(nuevo);
        dao.guardar(nuevo);
    }

    public List<Vehiculo> listar(){
        return vehiculos;
    }

    public Vehiculo buscarPorPlaca(String placa){
        for (Vehiculo v : vehiculos) {
            if(v.getPlaca().equalsIgnoreCase(placa)){
                return v;
            }
        }
        return null;
    }

    public boolean tieneCupos(String placa){
        Vehiculo v = buscarPorPlaca(placa);
        if(v==null){
            throw new IllegalArgumentException("No existe un vehiculo con la placa: "+placa);
        }
        return v.getPasajerosActuales()<v.getCapacidadMaxima();
    }

    public void asignarConductor(String placa, Conductor conductor){
        Vehiculo v = buscarPorPlaca(placa);
        if(v==null){
            throw new IllegalArgumentException("No existe un vehiculo con la placa: "+placa);
        }
        if(conductor.getLicencia()== null || conductor.getLicencia().isEmpty()){
            throw new IllegalArgumentException("El conductor no tiene licencia registrada: ");
        }
        // TODO: v.setConductor(conductor) cuando Dev 1 agregue el atributo
    }


}