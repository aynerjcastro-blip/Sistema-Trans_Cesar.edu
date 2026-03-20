package cesar.sistema_transcesar.dao;

import java.io.FileWriter;
import java.io.IOException;

import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

public class VehiculoDAO {
    private static final String ARCHIVO = "vehiculos.txt";
    
    private void guardar(Vehiculo vehiculo){
        try(FileWriter fw = new FileWriter(ARCHIVO,true)){
            fw.write(vehiculo.toString()+"\n");
        }catch(IOException e){
            System.out.println("Error al guardar el archivo: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
