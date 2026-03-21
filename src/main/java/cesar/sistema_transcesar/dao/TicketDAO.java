package cesar.sistema_transcesar.dao;

import cesar.sistema_transcesar.model.Ticket;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    private static final String ARCHIVO = "tickets.txt";

    // GUARDAR
    public void guardar(List<Ticket> tickets) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Ticket t : tickets) {
                bw.write(
                        t.getId() + ";" +
                                t.getPasajero().getIdentificacion() + ";" +
                                t.getVehiculo().getPlaca() + ";" +
                                t.getFecha() + ";" +
                                t.getTarifaBase()
                );
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar tickets: " + e.getMessage());
        }
    }

    // CARGAR
    public List<Ticket> cargar(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");

                int id = Integer.parseInt(datos[0]);
                String cedula = datos[1];
                String placa = datos[2];
                LocalDate fecha = LocalDate.parse(datos[3]);
                double tarifa = Double.parseDouble(datos[4]);

                Pasajero pasajero = buscarPasajero(cedula, pasajeros);
                Vehiculo vehiculo = buscarVehiculo(placa, vehiculos);

                if (pasajero != null && vehiculo != null) {
                    Ticket ticket = new Ticket(id, pasajero, vehiculo, fecha, tarifa);
                    tickets.add(ticket);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al cargar tickets: " + e.getMessage());
        }

        return tickets;
    }

    // BUSCAR PASAJERO
    private Pasajero buscarPasajero(String cedula, List<Pasajero> pasajeros) {
        for (Pasajero p : pasajeros) {
            if (p.getIdentificacion().equals(cedula)) {
                return p;
            }
        }
        return null;
    }

    // BUSCAR VEHICULO
    private Vehiculo buscarVehiculo(String placa, List<Vehiculo> vehiculos) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) {
                return v;
            }
        }
        return null;
    }
}