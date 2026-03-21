package cesar.sistema_transcesar.services;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;
import cesar.sistema_transcesar.model.reservas.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import cesar.sistema_transcesar.dao.ReservaDAO;

public class ReservaService {
    private List<Reserva> reservas;
    private ReservaDAO dao;
    private TicketService TicketService;
    private VehiculoService vehiculoService;
    private int contador;

    public ReservaService(TicketService ticketService, VehiculoService vehiculoService,List<Pasajero> pasajeros ,List<Vehiculo>vehiculos){
        this.dao= new ReservaDAO();
        this.TicketService=ticketService;
        this.vehiculoService=vehiculoService;
        this.reservas=dao.cargar(pasajeros, vehiculos);
        //verificarVencidas();
    }
    public void crearReserva(Pasajero pasajero, Vehiculo vehiculo, LocalDate fechaViaje) {
  // Validacion 1: cupos disponibles contando tickets + reservas activas
        long reservasActivas = reservas.stream()
            .filter(r -> r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca())
                    && r.getEstado().equals("Activa"))
            .count();
        int ocupados = vehiculo.getPasajerosActuales() + (int) reservasActivas;
        if (ocupados >= vehiculo.getCapacidadMaxima()) {
            throw new IllegalArgumentException("El vehiculo no tiene cupos disponibles.");
        }

        // Validacion 2: pasajero no puede tener dos reservas activas mismo vehiculo misma fecha
        for (Reserva r : reservas) {
            if (r.getPasajero().getIdentificacion().equalsIgnoreCase(pasajero.getIdentificacion())
                && r.getVehiculo().getPlaca().equalsIgnoreCase(vehiculo.getPlaca())
                && r.getFechaViaje().equals(fechaViaje)
                && r.getEstado().equals("Activa")) {
                throw new IllegalArgumentException(
                    "El pasajero ya tiene una reserva activa para ese vehiculo en esa fecha.");
            }
        }

        String codigo = "RES-" + contador++;
        Reserva nueva = new Reserva(codigo, pasajero, vehiculo,LocalDate.now(), fechaViaje, "Activa");
        reservas.add(nueva);
        dao.guardar(reservas);
        System.out.println("Reserva creada exitosamente. Codigo: " + codigo);
        
    }

    public void cancelarReserva(String codigo) {
        for (Reserva r : reservas) {
            if (r.getCodigo().equalsIgnoreCase(codigo)) {
                if (!r.getEstado().equals("Activa")) {
                    throw new IllegalArgumentException(
                        "Solo se pueden cancelar reservas Activas.");
                }
                r.setEstado("Cancelada");
                dao.guardar(reservas);
                System.out.println("Reserva " + codigo + " cancelada correctamente.");
                return;
            }
        }
        throw new IllegalArgumentException("No existe una reserva con el codigo: " + codigo);
    }

    public List<Reserva> listarActivas() {
        List<Reserva> activas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getEstado().equals("Activa")) activas.add(r);
        }
        return activas;
    }

    public List<Reserva> historialPasajero(String identificacion) {
        List<Reserva> historial = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getPasajero().getIdentificacion().equalsIgnoreCase(identificacion)) {
                historial.add(r);
            }
        }
        return historial;
    }
    
    public void convertirEnTicket(String codigo){
        for (Reserva r : reservas) {
            if(r.getCodigo().equalsIgnoreCase(codigo)){
            if(!r.getEstado().equals("Activa")){
                throw new IllegalArgumentException("Solo se pueden convertir reservas activas");
            }
            r.setEstado("Corregida");
            dao.guardar(reservas);
            System.out.println("Reserva convertidad en ticket correctamente.");
            return;
        }
        throw new IllegalArgumentException("No existe ninguna reserva con ese codigo: "+codigo);
        }
    }

    public void verificarVencidas() {
        int canceladas = 0;
        LocalDate hoy = LocalDate.now();
        for (Reserva r : reservas) {
            if (r.getEstado().equals("Activa")
                && r.getFechaCreacion().isBefore(hoy.minusDays(1))) {
                r.setEstado("Cancelada");
                canceladas++;
            }
        }
        if (canceladas > 0) {
            dao.guardar(reservas);
            System.out.println(canceladas + " reserva(s) vencidas canceladas automaticamente.");
        }
    }


}
