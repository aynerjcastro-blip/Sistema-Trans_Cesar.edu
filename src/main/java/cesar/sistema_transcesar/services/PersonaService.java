package cesar.sistema_transcesar.services;

import cesar.sistema_transcesar.model.dao.PersonaDAO;
import cesar.sistema_transcesar.model.personas.*;

public class PersonaService {

    private PersonaDAO personaDAO;

    public PersonaService() {
        this.personaDAO = new PersonaDAO();
    }

    //  Registrar conductor
    public void registrarConductor(String cedula, String nombre, String licencia, String categoria) {

        if (licencia == null || licencia.isEmpty()) {
            System.out.println("Licencia inválida");
            return;
        }

        Conductor conductor = new Conductor(cedula, nombre, licencia, categoria);
        personaDAO.guardar(conductor);

        System.out.println("Conductor registrado correctamente");
    }

    //  Registrar pasajero
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

        personaDAO.guardar(pasajero);

        System.out.println("Pasajero registrado correctamente");
    }
}