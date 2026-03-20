package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.model.Ticket;
import cesar.sistema_transcesar.model.dao.TicketDAO;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

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

    private final TicketDAO ticketDAO;
    private final List<Ticket> tickets;
    private int contador;

    // CORRECCIÓN: el constructor anterior no recibía parámetros y arrancaba
    // con lista vacía y contador en 1 siempre, lo que hacía que el límite
    // de 3 tickets por día (Req 2) no funcionara entre sesiones.
    // Ahora carga los tickets persistidos desde archivo al iniciar,
    // y calcula el contador desde el último ID real guardado.
    public TicketService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        this.ticketDAO = new TicketDAO();
        this.tickets   = new ArrayList<>(ticketDAO.cargar(pasajeros, vehiculos));
        // CORRECCIÓN: evita IDs duplicados entre sesiones
        this.contador  = tickets.isEmpty()
                ? 1
                : tickets.get(tickets.size() - 1).getId() + 1;
    }

    // ─── FESTIVOS ───────────────────────────────────────────────────

    private boolean esFestivo(LocalDate fecha) {
        return FESTIVOS.contains(fecha);
    }

    // ─── LÍMITE DIARIO ──────────────────────────────────────────────

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

        // Validar disponibilidad
        if (!vehiculo.isDisponible()) {
            System.out.println("Error: el vehiculo no esta disponible.");
            return null;
        }

        // Validar cupos
        if (vehiculo.getCuposDisponibles() <= 0) {
            System.out.println("Error: no hay cupos disponibles.");
            return null;
        }

        // Req 2 — validar límite de 3 tickets por pasajero por día
        int ticketsHoy = contarTicketsPorDia(pasajero, hoy);
        if (ticketsHoy >= 3) {
            System.out.println("Error: el pasajero ya tiene " + ticketsHoy
                    + " ticket(s) hoy. Limite diario alcanzado (max 3).");
            return null;
        }

        // Req 2 — aplicar +20% si es festivo antes de crear el ticket
        double tarifa = vehiculo.getTarifaBase();
        if (esFestivo(hoy)) {
            tarifa = tarifa * 1.20;
            System.out.println("Aviso: dia festivo — recargo del 20% aplicado.");
        }

        // Crear ticket — calcularTotal() aplica el descuento del pasajero internamente
        Ticket ticket = new Ticket(contador++, pasajero, vehiculo, hoy, tarifa);

        // Registrar en memoria
        tickets.add(ticket);

        // Actualizar ocupación del vehículo
        vehiculo.setPasajerosActuales(vehiculo.getPasajerosActuales() + 1);

        // Persistir en archivo
        ticketDAO.guardar(tickets);

        System.out.println("Ticket vendido correctamente.");
        ticket.imprimirDetalle();

        return ticket;
    }

    //  CONSULTAS

    public List<Ticket> listarTickets() {
        return tickets;
    }
}