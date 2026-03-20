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

    public TicketService() {
        this.ticketDAO = new TicketDAO();
        this.tickets = new ArrayList<>();
        this.contador = 1;
    }

    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo) {

        // Validar disponibilidad
        if (!vehiculo.isDisponible()) {
            System.out.println("Vehículo no disponible");
            return null;
        }

        //  Validar cupos
        if (vehiculo.getCuposDisponibles() <= 0) {
            System.out.println("No hay cupos disponibles");
            return null;
        }

        //  Crear ticket
        Ticket ticket = new Ticket(
                contador++,
                pasajero,
                vehiculo,
                LocalDate.now(),
                vehiculo.getTarifaBase()
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

    public List<Ticket> listarTickets() {
        return tickets;
    }
}