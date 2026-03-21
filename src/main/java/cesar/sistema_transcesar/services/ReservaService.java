package cesar.sistema_transcesar.services;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;
import cesar.sistema_transcesar.model.reservas.*;

import java.time.LocalDate;
import java.util.List;
import cesar.sistema_transcesar.dao.ReservaDAO;

public class ReservaService {
    private List<Reserva> reservas;
    private ReservaDAO dao;
    private TicketService TicketService;
    private VehiculoService vehiculoService;

    public ReservaService(TicketService ticketService, VehiculoService vehiculoService,List<Pasajero> pasajeros ,List<Vehiculo>vehiculos){
        this.dao= new ReservaDAO();
        this.TicketService=ticketService;
        this.vehiculoService=vehiculoService;
        this.reservas=dao.cargar(pasajeros, vehiculos);
        //verificarVencidas();
    }

    public void crearReserva(Pasajero pasajero,Vehiculo vehiculo, LocalDate fechaViaje){
        var reservasActivas = reservas.stream().
        filter(r -> r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca())
        && r.getEstado().equals("Activa")).count();
        int ocupados = vehiculo.getPasajerosActuales()+ (int) reservasActivas;
        if(ocupados>=vehiculo.getCapacidadMaxima()){
            throw new IllegalArgumentException("El vehiculo no tiene mas cupos disponibles");
        }
    }
    
}
