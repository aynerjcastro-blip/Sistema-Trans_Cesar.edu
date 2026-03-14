package cesar.sistema_transcesar.view;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner consola = new Scanner(System.in);

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

    // TODO: Actividad 3
    static void registrarVehiculo() {
        System.out.println("\n**Registrar vehiculo**");
        System.out.println("""
                Tipo:
                1. Buseta
                2. MicroBus
                3. Bus
                """);
                try{
                    var tipo = Integer.parseInt(consola.nextLine());
        
                    System.out.println("Placa: ");
                    var placa = consola.nextLine();

                    System.out.println("Ruta: ");
                    var estado = Integer.parseInt(consola.nextLine());
                    //TODO: VehiculoService.registrar(tipo,placa,estado
                }catch(InputMismatchException e){
                    System.out.println("Error: Debe ingresar un numero valido."+e.getMessage());
                    e.printStackTrace();
                }catch(IllegalArgumentException e){
                    System.out.println("Error: "+e.getMessage());
                    e.printStackTrace();
                }
    }

    // TODO: Actividad 3
    static void listarVehiculos() {
        System.out.println("\n** Listar Vehiculos **");
        //TODO: VehiculoService.listar();
        System.out.println("pendiente a integracion con service");
    }

    // TODO: Actividad 4
    static void registrarConductor() {
        System.out.println("\n** Registrar conductor **");
        try{
            System.out.println("Cedula: ");
            var cedula = consola.nextLine();

            System.out.println("Nombre: ");
            var nombre = consola.nextLine();

            System.out.println("Fecha de nacimiento: ");
            var fechaNacimiento = consola.nextLine();

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
        }catch(DateTimeParseException e){
            System.out.println("Error: formato de fecha invalido. Use dd/MM/yyyy"+e.getMessage());
            e.printStackTrace();
        }
    }

    // TODO: Actividad 4
    static void listarConductores() {
        System.out.println("\n ** Listar Conductores * *");
        //TODO: PersonaService.listarConductores()
        System.out.println("(pendiente a integracion con service)");
    }

    //TODO: Actividad 5
    static void registrarPasajero(){}
    static void listarPasajeros(){}

    // TODO: Actividad 6
    static void venderTicket() {}

    // TODO: Actividad 12
    static void verEstadisticas() {}
}