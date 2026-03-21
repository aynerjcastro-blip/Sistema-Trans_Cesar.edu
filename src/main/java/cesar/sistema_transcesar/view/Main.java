package cesar.sistema_transcesar.view;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import cesar.sistema_transcesar.services.PersonaService;
import cesar.sistema_transcesar.services.TicketService;
import cesar.sistema_transcesar.services.VehiculoService;
import cesar.sistema_transcesar.services.ReporteService;
import cesar.sistema_transcesar.services.ReservaService;
import cesar.sistema_transcesar.model.Ticket;
import cesar.sistema_transcesar.model.personas.Conductor;
import cesar.sistema_transcesar.model.personas.Pasajero;
import cesar.sistema_transcesar.model.vehiculos.Vehiculo;

public class Main {

    static Scanner consola = new Scanner(System.in);
    static VehiculoService vehiculoService = new VehiculoService();
    static PersonaService personaService = new PersonaService();
    static TicketService ticketService = new TicketService(
        personaService.listarPasajeros(),
        vehiculoService.listar()
    );
    static ReporteService reporteService = new ReporteService(ticketService);

    static ReservaService reservaService = new ReservaService(
    ticketService,
    vehiculoService,
    personaService.listarPasajeros(),
    vehiculoService.listar()
    );

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
                case 5: registrarPasajero(); break;
                case 6: listarPasajeros(); break;
                case 7: venderTicket(); break;
                case 8: verEstadisticas(); break;
                case 9: mostrarMenuReportes();break;
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
        System.out.println("4. Listar conductores");
        System.out.println("5. Registrar pasajero");
        System.out.println("6. Listar pasajeros");
        System.out.println("7. Vender ticket");
        System.out.println("8. Ver estadisticas");
        System.out.println("9. Reportes");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opcion: ");
    }

    static void registrarVehiculo() {
        System.out.println("\n** Registrar Vehiculo **");
        System.out.println("""
                Tipo:
                1. Buseta
                2. MicroBus
                3. Bus
                """);
        try {
            System.out.print("Tipo: ");
            var tipo = Integer.parseInt(consola.nextLine());

            System.out.print("Placa: ");
            var placa = consola.nextLine();

            System.out.print("Ruta: ");
            var ruta = consola.nextLine();

            System.out.print("Disponible (true/false): ");
            var disponible = Boolean.parseBoolean(consola.nextLine());

            vehiculoService.registrar(tipo, placa, ruta, disponible);
            System.out.println("Vehiculo registrado correctamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un numero valido.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void listarVehiculos() {
        System.out.println("\n** Lista de Vehiculos **");
        List<Vehiculo> lista = vehiculoService.listar();
        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos registrados.");
        } else {
            for (Vehiculo v : lista) {
                v.imprimirDetalle();
            }
        }
    }

    static void registrarConductor() {
        System.out.println("\n** Registrar Conductor **");
        try {
            System.out.print("Cedula: ");
            var cedula = consola.nextLine();

            System.out.print("Nombre: ");
            var nombre = consola.nextLine();

            System.out.print("Numero de licencia: ");
            var numeroDeLicencia = consola.nextLine();

            System.out.println("""
                    Categoria:
                    1. B1
                    2. B2
                    3. C1
                    4. C2
                    """);
            System.out.print("Seleccione categoria: ");
            var categoria = consola.nextLine();

            personaService.registrarConductor(cedula, nombre, numeroDeLicencia, categoria);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void listarConductores() {
        System.out.println("\n** Lista de Conductores **");
        List<Conductor> conductores = personaService.listarConductores();
        if (conductores.isEmpty()) {
            System.out.println("No hay conductores registrados.");
        } else {
            for (Conductor c : conductores) {
                c.imprimirDetalle();
            }
        }
    }

    static void registrarPasajero() {
        System.out.println("\n** Registrar Pasajero **");
        try {
            System.out.print("Identificacion: ");
            var identificacion = consola.nextLine();

            System.out.print("Nombre: ");
            var nombre = consola.nextLine();

            System.out.print("Fecha de nacimiento (dd/MM/yyyy): ");
            var fechaNacimiento = consola.nextLine();

            System.out.println("""
                    Tipo:
                    1. Regular
                    2. Estudiante
                    """);
            System.out.print("Seleccione tipo: ");
            var tipo = Integer.parseInt(consola.nextLine());

            personaService.registrarPasajero(identificacion, nombre, fechaNacimiento, tipo);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println("Error: formato de fecha invalido. Use dd/MM/yyyy");
        }
    }

    static void listarPasajeros() {
        System.out.println("\n** Lista de Pasajeros **");
        List<Pasajero> pasajeros = personaService.listarPasajeros();
        if (pasajeros.isEmpty()) {
            System.out.println("No hay pasajeros registrados.");
        } else {
            for (Pasajero p : pasajeros) {
                p.imprimirDetalle();
            }
        }
    }

    static void venderTicket() {
        System.out.println("\n** Vender Ticket **");
        try {
            System.out.print("Identificacion del pasajero: ");
            var identificacion = consola.nextLine();

            System.out.print("Placa del vehiculo: ");
            var placa = consola.nextLine();

            Pasajero pasajero = personaService.buscarPasajero(identificacion);
            if (pasajero == null) {
                System.out.println("Error: no existe un pasajero con esa identificacion.");
                return;
            }

            Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
            if (vehiculo == null) {
                System.out.println("Error: no existe un vehiculo con esa placa.");
                return;
            }

            ticketService.venderTicket(pasajero, vehiculo, LocalDate.now());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void verEstadisticas() {
        System.out.println("\n** Estadisticas del Sistema **");
        System.out.println("1. Total recaudado");
        System.out.println("2. Pasajeros por tipo");
        System.out.println("3. Vehiculo con mas tickets vendidos");
        System.out.print("Seleccione una opcion: ");
        try {
            int opcion = Integer.parseInt(consola.nextLine());
            switch (opcion) {
                case 1: mostrarTotalRecaudado(); break;
                case 2: mostrarPasajerosPorTipo(); break;
                case 3: mostrarVehiculoTop(); break;
                default: System.out.println("Opcion no valida.");
            }
        } catch (InputMismatchException e) {
            consola.nextLine();
            System.out.println("Error: debe ingresar un numero valido.");
        }
    }

    static void mostrarTotalRecaudado() {
        double total = ticketService.calcularTotalRecaudado();
        System.out.println("Total recaudado: $" + total);
    }

    static void mostrarPasajerosPorTipo() {
        ticketService.contarPasajerosPorTipo().forEach((tipo, cantidad) ->
            System.out.println(tipo + ": " + cantidad + " pasajeros"));
    }

    static void mostrarVehiculoTop() {
        Vehiculo top = ticketService.obtenerVehiculoTop();
        if (top != null) {
            top.imprimirDetalle();
        } else {
            System.out.println("No hay tickets registrados aun.");
        }
    }

    static void mostrarMenuReportes() {
    System.out.println("\n** Modulo de Reportes **");
    System.out.println("1. Tickets por fecha especifica");
    System.out.println("2. Tickets por tipo de vehiculo");
    System.out.println("3. Tickets por tipo de pasajero");
    System.out.println("4. Resumen del dia actual");
    System.out.print("Seleccione una opcion: ");
    try {
        int opcion = Integer.parseInt(consola.nextLine());
        switch (opcion) {
            case 1: reportePorFecha(); break;
            case 2: reportePorTipoVehiculo(); break;
            case 3: reportePorTipoPasajero(); break;
            case 4: reporteService.resumenDiaActual(); break;
            default: System.out.println("Opcion no valida.");
        }
    } catch (InputMismatchException e) {
        System.out.println("Error: debe ingresar un numero valido.");
    }
}


static void reportePorFecha() {
    System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
    String fechaStr = consola.nextLine();
    try {
        LocalDate fecha = LocalDate.parse(fechaStr, 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        List<Ticket> resultado = reporteService.reportePorFecha(fecha);
        if (resultado.isEmpty()) {
            System.out.println("No hay tickets para esa fecha.");
        } else {
            System.out.println("Tickets vendidos el " + fecha + ":");
            for (Ticket t : resultado) {
                t.imprimirDetalle();
            }
        }
    } catch (java.time.format.DateTimeParseException e) {
        System.out.println("Error: formato de fecha invalido. Use dd/MM/yyyy");
    }
}


static void reportePorTipoVehiculo() {
    System.out.println("""
            Tipo de vehiculo:
            1. Buseta
            2. MicroBus
            3. Bus
            """);
    System.out.print("Seleccione tipo: ");
    try {
        int opcion = Integer.parseInt(consola.nextLine());
        String tipo;
        switch (opcion) {
            case 1: tipo = "Buseta"; break;
            case 2: tipo = "MicroBus"; break;
            case 3: tipo = "Bus"; break;
            default:
                System.out.println("Opcion no valida.");
                return;
        }
        List<Ticket> resultado = reporteService.reportePorTipoVehiculo(tipo);
        if (resultado.isEmpty()) {
            System.out.println("No hay tickets para ese tipo de vehiculo.");
        } else {
            System.out.println("Tickets vendidos en " + tipo + ":");
            for (Ticket t : resultado) {
                t.imprimirDetalle();
            }
        }
    } catch (NumberFormatException e) {
        System.out.println("Error: debe ingresar un numero valido.");
    }
}

    static void reportePorTipoPasajero() {
        System.out.println("""
                Tipo de pasajero:
                1. PasajeroRegular
                2. PasajeroEstudiante
                3. PasajeroAdultoMayor
                """);
        System.out.print("Seleccione tipo: ");
        try {
            int opcion = Integer.parseInt(consola.nextLine());
            String tipo;
            switch (opcion) {
                case 1: tipo = "PasajeroRegular"; break;
                case 2: tipo = "PasajeroEstudiante"; break;
                case 3: tipo = "PasajeroAdultoMayor"; break;
                default:
                    System.out.println("Opcion no valida.");
                    return;
            }
            List<Ticket> resultado = reporteService.reportePorTipoPasajero(tipo);
            if (resultado.isEmpty()) {
                System.out.println("No hay tickets para ese tipo de pasajero.");
            } else {
                System.out.println("Tickets vendidos a " + tipo + ":");
                for (Ticket t : resultado) {
                    t.imprimirDetalle();
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: debe ingresar un numero valido.");
        }
    }
}