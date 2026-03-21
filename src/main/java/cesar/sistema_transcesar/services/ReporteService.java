package cesar.sistema_transcesar.services;
import cesar.sistema_transcesar.model.Ticket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReporteService {
    private TicketService ticketService;
    public ReporteService(TicketService ticketService){
        this.ticketService=ticketService;
    }

    public List<Ticket> reportePorFecha(LocalDate fecha){
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : ticketService.getTickets()) {
            if(t.getFecha().equals(fecha)){
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Ticket> reportePorTipoVehiculo(String tipo){
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : ticketService.getTickets()) {       
            if(t.getVehiculo().getClass().getSimpleName().equalsIgnoreCase(tipo)){
                resultado.add(t);
            }
        }
        return resultado;
    }

    public List<Ticket> reportePorTipoPasajero(String tipo) {
        List<Ticket> resultado = new ArrayList<>();
        for (Ticket t : ticketService.getTickets()) {
            if (t.getPasajero().getClass().getSimpleName().equalsIgnoreCase(tipo)) {
                resultado.add(t);
            }
        }
        return resultado;
    } 

    public void resumenDiaActual() {
        LocalDate hoy = LocalDate.now();
        List<Ticket> ticketsHoy = reportePorFecha(hoy);
        var total = 0;
        for (Ticket t : ticketsHoy) {
            total += t.calcularTotal();
        }
        System.out.println("=== Resumen del dia " + hoy + " ===");
        System.out.println("Tickets vendidos: " + ticketsHoy.size());
        System.out.println("Total recaudado: $" + total);
    }
}
