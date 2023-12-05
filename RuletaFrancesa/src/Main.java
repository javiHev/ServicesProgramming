import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean seguirJugando = true;

        // Crear la ruleta, la banca y los jugadores...
        Ruleta ruleta = new Ruleta();
        Banca banca = new Banca(10000);
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador(500)); // Jugador humano
        jugadores.add(new Jugador(500, true)); // Bot


        while (seguirJugando) {
            // Crear el croupier
            Croupier croupier = new Croupier(ruleta, banca, jugadores, 30);
            croupier.start();

            // Interacción con el jugador humano
            for (Jugador jugador : jugadores) {
                if (jugador.esHumano()) {
                    jugador.realizarApuesta(true);
                }else{
                    jugador.realizarApuesta(false);
                }
            }

            // Esperar a que el croupier termine
            try {
                croupier.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Mostrar resultados y actualizar banca
            for (Jugador jugador : jugadores) {
                if (jugador.esHumano()) {
                    System.out.println("\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0 Resultados de tu Apuesta \uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0");
                    for (Apuesta apuesta :
                            jugador.getApuestas()) {
                        System.out.println("Cantidad Apostada: " + apuesta.getMonto());
                        System.out.println("Tipo de apuesta: " + apuesta.getTipo());

                    }
                    System.out.println("Resultado de la apuesta " + jugador.getGanancia());
                    System.out.println("Saldo final: " + jugador.getSaldo());
                    System.out.println("\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0\uD83C\uDFB0");
                } else {
                    System.out.println("\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF Resultados de los demás Jugadores \uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF");
                    for (Apuesta apuesta :
                            jugador.getApuestas()) {
                        System.out.println("Cantidad Apostada: " + apuesta.getMonto());
                        System.out.println("Tipo de apuesta: " + apuesta.getTipo());

                    }
                    System.out.println("Resultado de la apuesta: " + jugador.getGanancia());
                    System.out.println("Saldo final: " + jugador.getSaldo());
                    System.out.println("\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF\uD83C\uDCCF");
                }

            }

            // Preguntar si quieren seguir jugando
            System.out.println("¿Desea jugar otra ronda? (si/no):");
            seguirJugando = scanner.next().equalsIgnoreCase("si");
            for (Jugador jugador : jugadores) {
                if (jugador.esHumano()) {
                    jugador.getApuestas().clear();
                }else{
                    jugador.getApuestas().clear();
                }
            }

        }

        System.out.println("Gracias por jugar. ¡Hasta la próxima!");
    }
}