import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jugador{
    private int saldo;
    private List<Apuesta> apuestas; // Usar una lista para manejar múltiples apuestas

    // Constructor para jugador humano
    public Jugador(int saldo) {
        this.saldo = saldo;
        this.apuestas = new ArrayList<>();
    }
    public Jugador(int saldo, boolean esBot) {
        this(saldo); // Llama al constructor de jugador humano
        if (esBot) {

            //Como no inizializo el bot no es necesario
        }
    }

    public int getSaldo() {
        return saldo;
    }


    public void realizarApuesta(boolean esHumano) {
        if (esHumano) {
            realizarApuestaHumano();
        } else {
            realizarApuestaBot();
        }
    }

    private void realizarApuestaHumano() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Saldo actual: " + saldo);

        // Tomar la cantidad de la apuesta
        System.out.println("Ingrese el monto de su apuesta:");
        int monto = scanner.nextInt();
        while (monto > saldo) {
            System.out.println("Saldo insuficiente. Ingrese un monto menor:");
            monto = scanner.nextInt();
        }

        // Elegir el tipo de apuesta
        System.out.println("Escoja entre estos tipos de apuesta:\n1. Número (0-36)\n2. Par/Impar\n3. Rojo/Negro\n4. 1-18/19-36\n5. Pasar");
        int eleccion = scanner.nextInt();
        while (eleccion < 1 || eleccion > 5) {
            System.out.println("Opción inválida. Por favor, elija una opción entre 1 y 5:");
            eleccion = scanner.nextInt();
        }

        // Crear y procesar la apuesta
        TipoApuesta tipoApuesta = TipoApuesta.fromInt(eleccion); // Suponiendo que tienes un enum TipoApuesta
        Apuesta apuesta = new Apuesta(monto, tipoApuesta);
        this.apuestas.add(apuesta);

        // Actualizar saldo
        saldo -= monto;
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

    public enum TipoApuesta {
        NUMERO("Número (0-36)"),
        PAR_IMPAR("Par/Impar"),
        ROJO_NEGRO("Rojo/Negro"),
        RANGO("1-18/19-36"),
        PASAR("Pasar");

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
                    return PAR_IMPAR;
                case 3:
                    return ROJO_NEGRO;
                case 4:
                    return RANGO;
                case 5:
                    return PASAR;
                default:
                    throw new IllegalArgumentException("Opción de apuesta no válida");
            }
        }
    }

}
