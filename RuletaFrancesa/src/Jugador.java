import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jugador{
    private final int saldoInicial;
    private int saldo;
    private List<Apuesta> apuestas; // Usar una lista para manejar múltiples apuestas
    boolean esBot=false;

    // Constructor para jugador humano
    public Jugador(int saldoInicial) {
        this.saldoInicial=saldoInicial;
        this.saldo = saldoInicial;
        this.apuestas = new ArrayList<>();
    }
    public Jugador(int saldo, boolean esBot) {
        this(saldo); // Llama al constructor de jugador humano
        this.esBot=esBot;
    }

    public int getSaldo() {
        return saldo;
    }
    public int getGanancia() {
        return saldo - saldoInicial;
    }



    public void realizarApuesta(boolean esHumano) {
        if (esHumano) {
            realizarApuestaHumano(true);
        } else {
            realizarApuestaBot();
        }
    }
    void realizarApuestaBot() {
        Random random = new Random();

        // Asegurarse de que el bot tenga saldo para apostar
        if (saldo > 0) {
            // Decidir aleatoriamente el monto de la apuesta, asegurándose de que no exceda el saldo
            int montoApuesta = 1 + random.nextInt(saldo);

            // Elegir un tipo de apuesta al azar
            TipoApuesta[] tipos = TipoApuesta.values();
            TipoApuesta tipoApuestaElegido = tipos[random.nextInt(tipos.length)];

            // Crear y añadir la apuesta
            Apuesta apuesta;
            if (tipoApuestaElegido == TipoApuesta.NUMERO) {
                // Elegir un número al azar para la apuesta a un número específico
                int numeroElegido = random.nextInt(37); // Números del 0 al 36
                apuesta = new Apuesta(montoApuesta, tipoApuestaElegido, numeroElegido);
            } else {
                apuesta = new Apuesta(montoApuesta, tipoApuestaElegido);
            }

            // Añadir la apuesta a la lista de apuestas del jugador
            this.apuestas.add(apuesta);

            // Restar el monto de la apuesta del saldo
            saldo -= montoApuesta;

        }


    }
   void realizarApuestaHumano(boolean seguirApostando) {
        Scanner scanner = new Scanner(System.in);


        while (seguirApostando && saldo > 0) {
            System.out.println("Saldo actual: " + saldo);

            // Tomar la cantidad de la apuesta
            System.out.println("Ingrese el monto de su apuesta:");
            int monto = scanner.nextInt();
            while (monto > saldo) {
                System.out.println("Saldo insuficiente. Ingrese un monto menor:");
                monto = scanner.nextInt();
            }

            // Elegir el tipo de apuesta
            // Mostrar las opciones de apuesta
            System.out.println("Escoja entre estos tipos de apuesta:");
            int opcion = 1;
            for (TipoApuesta tipo : TipoApuesta.values()) {
                System.out.println(opcion + ". " + tipo.getDescripcion());
                opcion++;
            }

            int eleccion = scanner.nextInt();
// Validar que la elección esté dentro del rango de opciones
            while (eleccion < 1 || eleccion > TipoApuesta.values().length) {
                System.out.println("Opción inválida. Por favor, elija una opción entre 1 y " + TipoApuesta.values().length + ":");
                eleccion = scanner.nextInt();
            }

            TipoApuesta tipoApuesta = TipoApuesta.fromInt(eleccion);
            Apuesta apuesta;
            if (tipoApuesta == TipoApuesta.NUMERO) {
                // Pedir el número específico para la apuesta
                System.out.println("Ingrese el número al que desea apostar (0-36):");
                int numeroEspecifico = scanner.nextInt();
                while (numeroEspecifico < 0 || numeroEspecifico > 36) {
                    System.out.println("Número inválido. Por favor, ingrese un número entre 0 y 36:");
                    numeroEspecifico = scanner.nextInt();
                }
                apuesta = new Apuesta(monto, tipoApuesta, numeroEspecifico);
            } else if (tipoApuesta != TipoApuesta.PASAR) {
                apuesta = new Apuesta(monto, tipoApuesta);
            } else {
                seguirApostando = false;
                continue;
            }

            this.apuestas.add(apuesta);
            saldo -= monto;

            // Preguntar si desea seguir apostando
            if (seguirApostando) {
                System.out.println("¿Desea realizar otra apuesta? (si/no):");
                seguirApostando = scanner.next().equalsIgnoreCase("si");

            }
        }

        if (saldo <= 0) {
            System.out.println("No tiene saldo suficiente para seguir apostando.");
        }
    }
    public List<Apuesta> getApuestas() {
        return apuestas;
    }


    public void ganarApuesta(int ganancia) {
        this.saldo += ganancia;
    }


    public void perderApuesta() {
        //
    }

    public boolean esHumano() {
        if(esBot){
            return true;
        }else {
            return false;
        }
    }

    public enum TipoApuesta {
        NUMERO("0-36"),
        PAR("Par"),
        IMPAR("Impar"),
        ROJO("Rojo"),
        NEGRO("Negro"),
        RANGO_INFERIOR("Rango Inferior (1-18)"),
        RANGO_SUPERIOR("Rango Superior (19-36)"), PASAR("PASAR");

        private final String descripcion;

        TipoApuesta(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }

        // Método estático para convertir un entero a un TipoApuesta
        public static TipoApuesta fromInt(int eleccion) {
            switch (eleccion) {
                case 1:
                    return NUMERO;
                case 2:
                    return PAR;
                case 3:
                    return IMPAR;
                case 4:
                    return ROJO;
                case 5:
                    return NEGRO;
                case 6:
                    return RANGO_INFERIOR;
                case 7:
                    return RANGO_SUPERIOR;
                case 8:
                    return PASAR;
                default:
                    throw new IllegalArgumentException("Opción de apuesta no válida");
            }
        }

    }

}
