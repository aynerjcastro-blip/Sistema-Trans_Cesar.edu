package cesar.sistema_transcesar.edu.dao;

import cesar.sistema_transcesar.edu.model.Conductor;
import cesar.sistema_transcesar.edu.model.Pasajero;
import cesar.sistema_transcesar.edu.model.PasajeroAdultoMayor;
import cesar.sistema_transcesar.edu.model.PasajeroEstudiante;
import cesar.sistema_transcesar.edu.model.PasajeroRegular;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private static final String CONDUCTORES_FILE = "conductores.txt";
    private static final String PASAJEROS_FILE = "pasajeros.txt";

    public void guardarConductor(Conductor c) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONDUCTORES_FILE, true))) {
            writer.write(c.getCedula() + "|" + c.getNombre() + "|" + c.getTelefono()
                    + "|" + c.getLicencia() + "|" + c.getCategoria());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public List<Conductor> cargarConductores() {
        List<Conductor> lista = new ArrayList<>();
        File file = new File(CONDUCTORES_FILE);
        if (!file.exists()) {
            return lista;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|");
                if (partes.length >= 5) {
                    lista.add(new Conductor(partes[0], partes[1], partes[2], partes[3], partes[4]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conductores: " + e.getMessage());
        }
        return lista;
    }

    public void guardarPasajero(Pasajero p) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PASAJEROS_FILE, true))) {
            writer.write(p.getCedula() + "|" + p.getNombre() + "|" + p.getTelefono()
                    + "|" + p.getTipoPasajero());
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> cargarPasajeros() {
        List<Pasajero> lista = new ArrayList<>();
        File file = new File(PASAJEROS_FILE);
        if (!file.exists()) {
            return lista;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                String[] partes = linea.split("\\|");
                if (partes.length >= 4) {
                    String cedula = partes[0];
                    String nombre = partes[1];
                    String telefono = partes[2];
                    String tipo = partes[3];
                    Pasajero p = switch (tipo) {
                        case "Estudiante" -> new PasajeroEstudiante(cedula, nombre, telefono);
                        case "AdultoMayor" -> new PasajeroAdultoMayor(cedula, nombre, telefono);
                        default -> new PasajeroRegular(cedula, nombre, telefono);
                    };
                    lista.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }
}
