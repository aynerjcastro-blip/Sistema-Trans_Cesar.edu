package cesar.sistema_transcesar.model.dao;

import cesar.sistema_transcesar.model.personas.Persona;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
}