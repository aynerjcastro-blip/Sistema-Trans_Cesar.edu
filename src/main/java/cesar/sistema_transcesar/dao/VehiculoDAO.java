package cesar.sistema_transcesar.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cesar.sistema_transcesar.model.vehiculos.*;

public class VehiculoDAO {
    private static final String ARCHIVO = "vehiculos.txt";
    
    public void guardar(Vehiculo vehiculo){
        try(FileWriter fw = new FileWriter(ARCHIVO,true)){
            fw.write(vehiculo.toString()+"\n");
        }catch(IOException e){
            System.out.println("Error al guardar el archivo: "+e.getMessage());
            e.printStackTrace();
        }
    }
        public List<Vehiculo> cargar(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))){
            String linea;
            while((linea = br.readLine()) != null){
                String[] datos = linea.split(";");
                if(datos.length == 4){
                    String placa = datos[0];
                    String ruta = datos[1];
                    String tipo = datos[2];
                    boolean disponible = Boolean.parseBoolean(datos[3]);
                    switch (tipo) {
                        case "Buseta" -> {vehiculos.add(new Buseta(placa, ruta, disponible));break;}
                        case "MicroBus" -> {vehiculos.add(new MicroBus(placa, ruta, disponible));break;}
                        case "Bus" -> {vehiculos.add(new Bus(placa, ruta, disponible));break;}
                        default -> {System.out.println("Tipo de vehículo no válido: " + tipo);}
                    }
                }
            }
        }catch(FileNotFoundException e){
            
        }catch(IOException e){
            System.out.println("Error al cargar el archivo: "+e.getMessage());
            e.printStackTrace();
        }
        return vehiculos;

    }

}
