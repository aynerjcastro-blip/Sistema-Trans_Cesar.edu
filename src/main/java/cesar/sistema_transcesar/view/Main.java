package cesar.sistema_transcesar.view;

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
                case 4: registrarPasajero(); break;
                case 5: venderTicket(); break;
                case 6: verEstadisticas(); break;
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
        var tipo = Integer.parseInt(consola.nextLine());
        
        System.out.println("Placa: ");
        var placa = consola.nextLine();

        System.out.println("Ruta: ");
        var estado = Integer.parseInt(consola.nextLine());

        //TODO: VehiculoService.registrar(tipo,placa,estado);

    }

    // TODO: Actividad 3
    static void listarVehiculos() {
        System.out.println("\n** Listar Vehiculos **");
        //TODO: VehiculoService.listar();
        System.out.println("pendiente a integracion con service");
    }

    // TODO: Actividad 4
    static void registrarConductor() {}

    // TODO: Actividad 5
    static void registrarPasajero() {}

    // TODO: Actividad 6
    static void venderTicket() {}

    // TODO: Actividad 12
    static void verEstadisticas() {}
}