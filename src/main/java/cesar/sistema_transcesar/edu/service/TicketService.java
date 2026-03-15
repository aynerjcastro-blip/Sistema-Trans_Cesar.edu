package cesar.sistema_transcesar.edu.service;

import cesar.sistema_transcesar.edu.dao.TicketDAO;
import cesar.sistema_transcesar.edu.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private List<Ticket> tickets;
    private TicketDAO dao;

    public TicketService() {
        this.dao = new TicketDAO();
        this.tickets = new ArrayList<>(dao.cargarTickets());
    }

    public boolean venderTicket(Ticket t) {
        if (!puedeComprar(t.getPasajero().getCedula(), t.getFechaViaje())) {
            int count = contarTicketsPorPasajeroPorDia(t.getPasajero().getCedula(), t.getFechaViaje());
            System.out.println("El pasajero ya tiene " + count + " tickets para el día "
                    + t.getFechaViaje() + ". No puede comprar más de 3.");
            return false;
        }
        tickets.add(t);
        dao.guardarTicket(t);
        return true;
    }

    public List<Ticket> listarTickets() {
        return tickets;
    }

    public List<Ticket> buscarPorFecha(String fecha) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : tickets) {
            if (t.getFechaViaje().equals(fecha)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    public double totalRecaudado() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.calcularTotal();
        }
        return total;
    }

    public int contarTicketsPorPasajeroPorDia(String cedulaPasajero, String fecha) {
        int count = 0;
        for (Ticket t : tickets) {
            if (t.getPasajero().getCedula().equals(cedulaPasajero)
                    && t.getFechaViaje().equals(fecha)) {
                count++;
            }
        }
        return count;
    }

    public boolean puedeComprar(String cedulaPasajero, String fecha) {
        return contarTicketsPorPasajeroPorDia(cedulaPasajero, fecha) < 3;
    }
}
