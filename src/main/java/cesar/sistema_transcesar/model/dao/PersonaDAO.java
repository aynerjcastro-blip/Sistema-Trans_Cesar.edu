package cesar.sistema_transcesar.model.dao;

import cesar.sistema_transcesar.model.personas.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    // CORRECCIÓN: antes había un solo "personas.txt" que mezclaba conductores
    // y pasajeros, perdiendo licencia, categoría y tipo. Ahora son dos archivos
    // separados con todos los campos necesarios para reconstruir cada objeto.
    private static final String ARCHIVO_CONDUCTORES = "conductores.txt";
    private static final String ARCHIVO_PASAJEROS   = "pasajeros.txt";

    // ─── CONDUCTORES

    public void guardarConductor(Conductor conductor) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CONDUCTORES, true))) {
            // CORRECCIÓN: antes solo guardaba cedula y nombre.
            // Ahora guarda cedula,nombre,licencia,categoria
            pw.println(
                    conductor.getCedula()   + "," +
                            conductor.getNombre()   + "," +
                            conductor.getLicencia() + "," +
                            conductor.getCategoria()
            );
        } catch (IOException e) {
            System.out.println("Error al guardar conductor: " + e.getMessage());
        }
    }

    public List<Conductor> cargarConductores() {
        List<Conductor> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_CONDUCTORES);
        // CORRECCIÓN: antes no verificaba si el archivo existía, lanzaba excepción
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] d = linea.split(",");
                // CORRECCIÓN: antes reconstruía con "new Persona(...){}" anónimo
                // inútil. Ahora reconstruye un Conductor real con todos sus campos
                lista.add(new Conductor(d[0], d[1], d[2], d[3]));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar conductores: " + e.getMessage());
        }
        return lista;
    }

    //  PASAJEROS

    public void guardarPasajero(Pasajero pasajero) {
        // CORRECCIÓN: antes no guardaba el tipo, imposibilitando reconstruir
        // la subclase correcta al cargar
        String tipo;
        if (pasajero instanceof PasajeroEstudiante)       tipo = "estudiante";
        else if (pasajero instanceof PasajeroAdultoMayor) tipo = "adultomayor";
        else                                               tipo = "regular";

        try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_PASAJEROS, true))) {
            pw.println(
                    pasajero.getCedula() + "," +
                            pasajero.getNombre() + "," +
                            tipo
            );
        } catch (IOException e) {
            System.out.println("Error al guardar pasajero: " + e.getMessage());
        }
    }

    public List<Pasajero> cargarPasajeros() {
        List<Pasajero> lista = new ArrayList<>();
        File archivo = new File(ARCHIVO_PASAJEROS);
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] d = linea.split(",");
                // CORRECCIÓN: antes reconstruía con "new Persona(...){}" anónimo.
                // Ahora instancia la subclase real según el tipo guardado,
                // preservando el polimorfismo de calcularDescuento()
                Pasajero p = switch (d[2].toLowerCase()) {
                    case "estudiante"  -> new PasajeroEstudiante(d[0], d[1]);
                    case "adultomayor" -> new PasajeroAdultoMayor(d[0], d[1]);
                    default            -> new PasajeroRegular(d[0], d[1]);
                };
                lista.add(p);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pasajeros: " + e.getMessage());
        }
        return lista;
    }
}