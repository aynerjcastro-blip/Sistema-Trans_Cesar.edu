package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.model.dao.PersonaDAO;
import cesar.sistema_transcesar.model.personas.*;

import java.util.List;

public class PersonaService {

    // CORRECCIÓN: antes solo validaba que la licencia no fuera vacía.
    // El PDF exige categorías B1, B2, C1, C2 — se valida contra esta lista
    private static final List<String> CATEGORIAS_VALIDAS = List.of("B1", "B2", "C1", "C2");

    private PersonaDAO personaDAO;

    public PersonaService() {
        this.personaDAO = new PersonaDAO();
    }

    // ─── CONDUCTOR ──────────────────────────────────────────────────

    public void registrarConductor(String cedula, String nombre, String licencia, String categoria) {

        if (cedula == null || cedula.isBlank()) {
            System.out.println("Error: la cedula no puede estar vacia.");
            return;
        }
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: el nombre no puede estar vacio.");
            return;
        }
        if (licencia == null || licencia.isBlank()) {
            System.out.println("Error: el numero de licencia no puede estar vacio.");
            return;
        }
        // CORRECCIÓN: validación real de categoría según el PDF (B1, B2, C1, C2)
        if (!CATEGORIAS_VALIDAS.contains(categoria.toUpperCase())) {
            System.out.println("Error: categoria invalida. Use B1, B2, C1 o C2.");
            return;
        }

        Conductor conductor = new Conductor(cedula, nombre, licencia, categoria.toUpperCase());
        // CORRECCIÓN: antes llamaba personaDAO.guardar() genérico que perdía
        // licencia y categoría. Ahora llama guardarConductor()
        personaDAO.guardarConductor(conductor);
        System.out.println("Conductor registrado correctamente.");
    }

    public List<Conductor> listarConductores() {
        return personaDAO.cargarConductores();
    }

    // ─── PASAJERO ───────────────────────────────────────────────────

    public void registrarPasajero(String cedula, String nombre, String tipo) {

        if (cedula == null || cedula.isBlank()) {
            System.out.println("Error: la cedula no puede estar vacia.");
            return;
        }
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: el nombre no puede estar vacio.");
            return;
        }

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

        // CORRECCIÓN: antes llamaba personaDAO.guardar() genérico que perdía
        // el tipo del pasajero. Ahora llama guardarPasajero()
        personaDAO.guardarPasajero(pasajero);
        System.out.println("Pasajero registrado. Tipo: " + pasajero.getClass().getSimpleName());
    }

    public List<Pasajero> listarPasajeros() {
        return personaDAO.cargarPasajeros();
    }
}