package cesar.sistema_transcesar.dao;

import cesar.sistema_transcesar.model.reservas.Reserva;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {

    private static final String ARCHIVO = "reservas.txt";

    public void guardar(List<Reserva> reservas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Reserva r : reservas) {
                bw.write(r.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar reservas: " + e.getMessage());
        }
    }

    public List<Reserva> cargar(List<Pasajero> pasajeros, List<Vehiculo> vehiculos) {
        List<Reserva> reservas = new ArrayList<>();
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return reservas;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] d = linea.split(",");
                if (d.length < 6) continue; // protección
                String codigo           = d[0];
                String cedulaPasajero   = d[1];
                String placaVehiculo    = d[2];
                LocalDate fechaCreacion = LocalDate.parse(d[3]);
                LocalDate fechaViaje    = LocalDate.parse(d[4]);
                String estado           = d[5];
                Pasajero pasajero = buscarPasajero(cedulaPasajero, pasajeros);
                Vehiculo vehiculo = buscarVehiculo(placaVehiculo, vehiculos);

                if (pasajero != null && vehiculo != null) {
                    reservas.add(new Reserva(codigo, pasajero, vehiculo, fechaCreacion, fechaViaje, estado));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar reservas: " + e.getMessage());
        }

        return reservas;
    }

    private Pasajero buscarPasajero(String identificacion, List<Pasajero> pasajeros) {
        for (Pasajero p : pasajeros) {
            if (p.getIdentificacion().equalsIgnoreCase(identificacion)) return p;
        }
        return null;
    }

    private Vehiculo buscarVehiculo(String placa, List<Vehiculo> vehiculos) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equals(placa)) return v;
        }
        return null;
    }
}