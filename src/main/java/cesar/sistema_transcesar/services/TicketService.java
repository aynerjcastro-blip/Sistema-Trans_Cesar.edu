package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.model.Ticket;
import cesar.sistema_transcesar.model.dao.TicketDAO;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private TicketDAO ticketDAO;
    private List<Ticket> tickets;
    private int contador;

    //FESTIVOS COLOMBIA
    private static final List<LocalDate> FESTIVOS = List.of(
            LocalDate.of(2026, 1, 1),
            LocalDate.of(2026, 1, 12),
            LocalDate.of(2026, 3, 23),
            LocalDate.of(2026, 4, 2),
            LocalDate.of(2026, 4, 3),
            LocalDate.of(2026, 5, 1),
            LocalDate.of(2026, 5, 18),
            LocalDate.of(2026, 6, 8),
            LocalDate.of(2026, 6, 15),
            LocalDate.of(2026, 6, 29),
            LocalDate.of(2026, 7, 20),
            LocalDate.of(2026, 8, 7),
            LocalDate.of(2026, 8, 17),
            LocalDate.of(2026, 10, 12),
            LocalDate.of(2026, 11, 2),
            LocalDate.of(2026, 11, 16),
            LocalDate.of(2026, 12, 8),
            LocalDate.of(2026, 12, 25)
    );

    public TicketService() {
        this.ticketDAO = new TicketDAO();
        this.tickets = new ArrayList<>();
        this.contador = 1;
    }

    //  VALIDAR FESTIVO
    private boolean esFestivo(LocalDate fecha) {
        return FESTIVOS.contains(fecha);
    }

    //  CONTAR TICKETS DEL PASAJERO EN EL DÍA
    private int contarTicketsPorDia(Pasajero pasajero, LocalDate fecha) {
        int count = 0;

        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equals(pasajero.getCedula())
                    && t.getFecha().equals(fecha)) {
                count++;
            }
        }

        return count;
    }

    //  VENDER TICKET
    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo) {

        LocalDate hoy = LocalDate.now();

        //  Validar disponibilidad
        if (!vehiculo.isDisponible()) {
            System.out.println("Vehículo no disponible");
            return null;
        }

        //  Validar cupos
        if (vehiculo.getCuposDisponibles() <= 0) {
            System.out.println("No hay cupos disponibles");
            return null;
        }

        //  Validar límite de 3 tickets por día
        int ticketsHoy = contarTicketsPorDia(pasajero, hoy);
        if (ticketsHoy >= 3) {
            System.out.println("Límite alcanzado: ya tienes " + ticketsHoy + " tickets hoy");
            return null;
        }

        //  Aplicar tarifa base
        double tarifa = vehiculo.getTarifaBase();

        //  Si es festivo → +20%
        if (esFestivo(hoy)) {
            tarifa = tarifa * 1.20;
        }

        //  Crear ticket
        Ticket ticket = new Ticket(
                contador++,
                pasajero,
                vehiculo,
                hoy,
                tarifa
        );

        //  Guardar en memoria
        tickets.add(ticket);

        //  Actualizar vehículo
        vehiculo.setPasajerosActuales(
                vehiculo.getPasajerosActuales() + 1
        );

        //  Guardar en archivo
        ticketDAO.guardar(tickets);

        System.out.println("Ticket vendido correctamente");

        return ticket;
    }

    //  LISTAR
    public List<Ticket> listarTickets() {
        return tickets;
    }
}