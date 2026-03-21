package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.model.Ticket;
import cesar.sistema_transcesar.model.dao.TicketDAO;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

import java.time.LocalDate;
import java.util.*;

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

    public TicketService(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        this.ticketDAO = new TicketDAO();
        this.tickets   = new ArrayList<>(ticketDAO.cargar(pasajeros, vehiculos));
        this.contador  = tickets.isEmpty()
                ? 1
                : tickets.get(tickets.size() - 1).getId() + 1;
    }

    // FESTIVOS

    private boolean esFestivo(LocalDate fecha) {
        return FESTIVOS.contains(fecha);
    }

    // LÍMITE DIARIO

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

    // VENDER TICKET

    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo) {
        return venderTicket(pasajero, vehiculo, LocalDate.now());
    }

    // SOBRECARGA: vender con fecha específica (anticipa Req 4 - reservas)
    public Ticket venderTicket(Pasajero pasajero, Vehiculo vehiculo, LocalDate fecha) {

        if (!vehiculo.isDisponible()) {
            System.out.println("Error: el vehiculo no esta disponible.");
            return null;
        }

        if (vehiculo.getCuposDisponibles() <= 0) {
            System.out.println("Error: no hay cupos disponibles.");
            return null;
        }

        int ticketsHoy = contarTicketsPorDia(pasajero, fecha);
        if (ticketsHoy >= 3) {
            System.out.println("Error: el pasajero ya tiene " + ticketsHoy
                    + " ticket(s) ese dia. Limite diario alcanzado (max 3).");
            return null;
        }

        double tarifa = vehiculo.getTarifaBase();
        if (esFestivo(fecha)) {
            tarifa = tarifa * 1.20;
            System.out.println("Aviso: dia festivo — recargo del 20% aplicado.");
        }

        Ticket ticket = new Ticket(contador++, pasajero, vehiculo, fecha, tarifa);
        tickets.add(ticket);
        vehiculo.setPasajerosActuales(vehiculo.getPasajerosActuales() + 1);
        ticketDAO.guardar(tickets);

        System.out.println("Ticket vendido correctamente.");
        ticket.imprimirDetalle();

        return ticket;
    }

    //  CONSULTAS

    public List<Ticket> listarTickets() {
        return tickets;
    }

    //  ESTADÍSTICAS (Actividad 12)

    // 1. Total recaudado
    public double calcularTotalRecaudado() {
        double total = 0;
        for (Ticket t : tickets) {
            total += t.calcularTotal();
        }
        return total;
    }

    // 2. Pasajeros por tipo
    public Map<String, Integer> contarPasajerosPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Ticket t : tickets) {
            String tipo = t.getPasajero().getClass().getSimpleName();
            conteo.put(tipo, conteo.getOrDefault(tipo, 0) + 1);
        }
        return conteo;
    }

    // 3. Vehiculo con mas tickets vendidos
    public Vehiculo obtenerVehiculoTop() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Ticket t : tickets) {
            String placa = t.getVehiculo().getPlaca();
            conteo.put(placa, conteo.getOrDefault(placa, 0) + 1);
        }
        return conteo.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(e -> buscarVehiculoPorPlaca(e.getKey()))
                .orElse(null);
    }

    private Vehiculo buscarVehiculoPorPlaca(String placa) {
        for (Ticket t : tickets) {
            if (t.getVehiculo().getPlaca().equals(placa)) {
                return t.getVehiculo();
            }
        }
        return null;
    }
}