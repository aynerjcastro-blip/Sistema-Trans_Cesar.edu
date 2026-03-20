package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.model.dao.PersonaDAO;
import cesar.sistema_transcesar.model.personas.*;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PersonaService {

    // Validación de categoría según el PDF (B1, B2, C1, C2)
    private static final List<String> CATEGORIAS_VALIDAS = List.of("B1", "B2", "C1", "C2");

    private PersonaDAO personaDAO;

    public PersonaService() {
        this.personaDAO = new PersonaDAO();
    }

    //  CONDUCTOR

    public void registrarConductor(String cedula, String nombre, String licencia, String categoria) {
        if (licencia == null || licencia.isBlank()) {
            throw new IllegalArgumentException("El conductor debe tener licencia registrada.");
        }
        // CORRECCIÓN: el PersonaService del Líder eliminó esta validación
        if (!CATEGORIAS_VALIDAS.contains(categoria.toUpperCase())) {
            throw new IllegalArgumentException("Categoria invalida. Use B1, B2, C1 o C2.");
        }
        Conductor conductor = new Conductor(cedula, nombre, licencia, categoria.toUpperCase());
        personaDAO.guardarConductor(conductor);
    }

    public List<Conductor> listarConductores() {
        return personaDAO.cargarConductores();
    }

    public Conductor buscarConductor(String cedula) {
        for (Conductor c : personaDAO.cargarConductores()) {
            if (c.getCedula().equalsIgnoreCase(cedula)) {
                return c;
            }
        }
        return null;
    }

    //  PASAJERO


    public void registrarPasajero(String identificacion, String nombre, String fechaNacimiento, int tipo) {
        LocalDate nacimiento;
        try {
            nacimiento = LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha invalido. Use dd/MM/yyyy");
        }

        int edad = Period.between(nacimiento, LocalDate.now()).getYears();

        Pasajero pasajero;
        if (edad >= 60) {
            // Adulto mayor asignado automáticamente por edad — sin importar lo que elija el usuario
            pasajero = new PasajeroAdultoMayor(identificacion, nombre);
        } else if (tipo == 2) {
            pasajero = new PasajeroEstudiante(identificacion, nombre);
        } else {
            pasajero = new PasajeroRegular(identificacion, nombre);
        }

        personaDAO.guardarPasajero(pasajero);
    }


    public void registrarPasajero(String cedula, String nombre, String tipo) {
        Pasajero pasajero;
        switch (tipo.toLowerCase()) {
            case "estudiante":
                pasajero = new PasajeroEstudiante(cedula, nombre);
                break;
            case "adulto":
            case "adultomayor":
                pasajero = new PasajeroAdultoMayor(cedula, nombre);
                break;
            default:
                pasajero = new PasajeroRegular(cedula, nombre);
                break;
        }
        personaDAO.guardarPasajero(pasajero);
    }

    public List<Pasajero> listarPasajeros() {
        return personaDAO.cargarPasajeros();
    }

    public Pasajero buscarPasajero(String identificacion) {
        for (Pasajero p : personaDAO.cargarPasajeros()) {
            if (p.getCedula().equalsIgnoreCase(identificacion)) {
                return p;
            }
        }
        return null;
    }
}