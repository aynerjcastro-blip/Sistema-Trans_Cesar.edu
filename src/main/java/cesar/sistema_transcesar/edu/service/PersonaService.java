package cesar.sistema_transcesar.edu.service;

import cesar.sistema_transcesar.edu.dao.PersonaDAO;
import cesar.sistema_transcesar.edu.model.Conductor;
import cesar.sistema_transcesar.edu.model.Pasajero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonaService {

    private List<Conductor> conductores;
    private List<Pasajero> pasajeros;
    private PersonaDAO dao;

    private static final List<String> CATEGORIAS_VALIDAS = Arrays.asList("B1", "B2", "C1", "C2");

    public PersonaService() {
        this.dao = new PersonaDAO();
        this.conductores = new ArrayList<>(dao.cargarConductores());
        this.pasajeros = new ArrayList<>(dao.cargarPasajeros());
    }

    public boolean registrarConductor(Conductor c) {
        if (buscarConductorPorCedula(c.getCedula()) != null) {
            System.out.println("Error: Ya existe un conductor con cedula " + c.getCedula());
            return false;
        }
        if (!CATEGORIAS_VALIDAS.contains(c.getCategoria())) {
            System.out.println("Error: Categoria invalida. Debe ser B1, B2, C1 o C2.");
            return false;
        }
        conductores.add(c);
        dao.guardarConductor(c);
        return true;
    }

    public boolean registrarPasajero(Pasajero p) {
        if (buscarPajajeroPorCedula(p.getCedula()) != null) {
            System.out.println("Error: Ya existe un pasajero con cedula " + p.getCedula());
            return false;
        }
        pasajeros.add(p);
        dao.guardarPasajero(p);
        return true;
    }

    public List<Conductor> listarConductores() {
        return conductores;
    }

    public List<Pasajero> listarPasajeros() {
        return pasajeros;
    }

    public Conductor buscarConductorPorCedula(String cedula) {
        for (Conductor c : conductores) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    public Pasajero buscarPajajeroPorCedula(String cedula) {
        for (Pasajero p : pasajeros) {
            if (p.getCedula().equals(cedula)) {
                return p;
            }
        }
        return null;
    }
}
