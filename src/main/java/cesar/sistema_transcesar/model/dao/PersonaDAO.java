package cesar.sistema_transcesar.model.dao;

import cesar.sistema_transcesar.model.personas.Persona;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    private static final String ARCHIVO = "personas.txt";


    public void guardar(Persona persona) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(persona.getCedula() + "," + persona.getNombre());

        } catch (IOException e) {
            System.out.println("Error al guardar persona: " + e.getMessage());
        }
    }


    public List<Persona> cargar() {
        List<Persona> personas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String cedula = datos[0];
                String nombre = datos[1];

                personas.add(new Persona(cedula, nombre) {});
            }

        } catch (IOException e) {
            System.out.println("Error al cargar personas: " + e.getMessage());
        }

        return personas;
    }
}