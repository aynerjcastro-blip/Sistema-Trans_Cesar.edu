package cesar.sistema_transcesar.edu.dao;

import cesar.sistema_transcesar.edu.model.PasajeroRegular;
import cesar.sistema_transcesar.edu.model.Ticket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private static final String TICKETS_FILE = "tickets.txt";

    public void guardarTicket(Ticket t) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TICKETS_FILE, true))) {
            writer.write(t.getIdTicket() + "|"
                    + t.getPasajero().getCedula() + "|"
                    + t.getPasajero().getNombre() + "|"
                    + t.getPasajero().getTipoPasajero() + "|"
                    + t.getPlacaVehiculo() + "|"
                    + t.getFechaViaje() + "|"
                    + t.getTarifaBase());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar ticket: " + e.getMessage());
        }
    }

    public List<Ticket> cargarTickets() {
        List<Ticket> lista = new ArrayList<>();
        File file = new File(TICKETS_FILE);
        if (!file.exists()) {
            return lista;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|");
                if (partes.length >= 7) {
                    int id = Integer.parseInt(partes[0]);
                    String cedula = partes[1];
                    String nombre = partes[2];
                    String placa = partes[4];
                    String fecha = partes[5];
                    double tarifa = Double.parseDouble(partes[6]);
                    PasajeroRegular pasajero = new PasajeroRegular(cedula, nombre, "");
                    lista.add(new Ticket(id, pasajero, placa, fecha, tarifa));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }
        return lista;
    }
}
