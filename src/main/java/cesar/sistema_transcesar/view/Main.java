package cesar.sistema_transcesar.view;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;


import cesar.sistema_transcesar.services.VehiculoService;
import cesar.sistema_transcesar.model.*;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;;

public class Main {

    static Scanner consola = new Scanner(System.in);
    static VehiculoService vehiculoService = new VehiculoService();


    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = consola.nextInt();
            consola.nextLine();
            switch (opcion) {
                case 1: registrarVehiculo(); break;
                case 2: listarVehiculos(); break;
                case 3: registrarConductor(); break;
                case 4: listarConductores(); break;
                case 5: registrarPasajero();break;
                case 6: listarPasajeros();break;
                case 7: venderTicket(); break;
                case 8: verEstadisticas(); break;
                case 0: System.out.println("Saliendo del sistema..."); break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    static void mostrarMenu() {
        System.out.println("\n====== TransCesar S.A.S. ======");
        System.out.println("1. Registrar vehiculo");
        System.out.println("2. Listar vehiculos");
        System.out.println("3. Registrar conductor");
        System.out.println("4. Registrar pasajero");
        System.out.println("5. Vender ticket");
        System.out.println("6. Ver estadisticas");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opcion: ");
    }


    static void registrarVehiculo() {
        System.out.println("\n**Registrar vehiculo**");
        System.out.println("""
                Tipo:
                1. Buseta
                2. MicroBus
                3. Bus
                """);
                try{
                    System.out.println("Tipo:");
                    var tipo = Integer.parseInt(consola.nextLine());
        
                    System.out.println("Placa: ");
                    var placa = consola.nextLine();

                    System.out.println("Ruta: ");
                    var ruta = consola.nextLine();

                    System.out.println("Estado: ");
                    var disponible=  Boolean.parseBoolean(consola.nextLine());

                    vehiculoService.registrar(tipo, placa, ruta, disponible);

                }catch(InputMismatchException e){
                    System.out.println("Error: Debe ingresar un numero valido."+e.getMessage());
                    e.printStackTrace();
                }catch(IllegalArgumentException e){
                    System.out.println("Error: "+e.getMessage());
                    e.printStackTrace();
                }
    }

    
    static void listarVehiculos() {
        System.out.println("\n** Listar Vehiculos **");
        List<Vehiculo> lista = vehiculoService.listar();
        if(lista.isEmpty()){
            System.out.println("No hay vehiculos registrados.");
        }else{
            for (Vehiculo v : lista) {
                v.imprimirDetalle();
            }
        }
    }

    
    static void registrarConductor() {
        System.out.println("\n** Registrar conductor **");
        try{
            System.out.println("Cedula: ");
            var cedula = consola.nextLine();

            System.out.println("Nombre: ");
            var nombre = consola.nextLine();

            System.out.println("Numero de Licencia: ");
            var numeroDeLicencia = consola.nextLine();

            System.out.println("""
                Categoria: 
                1. B1
                2. B2
                3. C1
                4. C2
                Seleccione categoria:
                """);
                var categoria = Integer.parseInt(consola.nextLine());

                //TODO: PersonaService.registrarConductor(cedula,nombre,fechaNacimiento,numeroDeLicencia,categoria)
        }catch(IllegalArgumentException e){
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }


    static void listarConductores() {
        System.out.println("\n ** Listar Conductores **");
        //TODO: PersonaService.listarConductores()
        System.out.println("(pendiente a integracion con service)");
    }

    
    static void registrarPasajero(){
        System.out.println("\n **Registrar Pasajeros **");
        try{
            
        
        System.out.println("Identificacion: ");
        var identificacion = consola.nextLine();

        System.out.println("Nombre: ");
        var nombre = consola.nextLine();

        System.out.println("Fecha de Nacimiento: (dd/mm/aaaa/): ");
        var fechaNacimiento = consola.nextLine();

        System.out.println("""
                Tipo:
                1. Regular
                2. Estudiante
                Seleccione tipo: 
                """);
                var tipo = Integer.parseInt(consola.nextLine());

                //TODO: PersonaService.registrarPasajero(cedula,nombre,fechaNacimiento,tipo)
                }catch(IllegalArgumentException e){
                    System.out.println("Error: "+e.getMessage());
                    e.printStackTrace();
                }catch(DateTimeParseException e){
                    System.out.println("Error: formato de fercha valido. Use dd/mm/yyyy"+e.getMessage());
                    e.printStackTrace();
                }
    }
    static void listarPasajeros(){
        System.out.println("\n ** Lista de Pasajeros **");
        System.out.println("pendiente a integracion con service");
    }

    
    static void venderTicket() {
        System.out.println("\n ** Vender Ticket **");

        System.out.println("Identificacion de pasajero: ");
        var identificacion = consola.nextLine();

        System.out.println("Placa del vehiculo: ");
        var placa = consola.nextLine();

        System.out.println("Seleccione una ruta disponible: ");
        //TODO: mostrar lista de rutas disponibles desde service

        System.out.println("Codigo de la ruta: ");
        var codigoRuta = consola.nextLine();
        try {
            //TODO: TicketService.venderTicket(identificacion,placa,ruta,codigoRuta)
            System.out.println("Ticket vendido correctamente: ");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }
    

    // TODO: Actividad 12
    static void verEstadisticas() {}
}