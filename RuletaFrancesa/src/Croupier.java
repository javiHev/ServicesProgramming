import java.util.List;

public class Croupier extends Thread {
    private Ruleta ruleta;
    private Banca banca;
    private List<Jugador> jugadores;
    private int segundos;

    public Croupier(Ruleta ruleta, Banca banca, List<Jugador> jugadores, int segundos) {
        this.ruleta = ruleta;
        this.banca = banca;
        this.jugadores = jugadores;
        this.segundos = segundos;
    }

    public void jugar() {
        ruleta.girar();
        int numeroGanador = ruleta.getNumeroGanador();
        for (Jugador jugador : jugadores) {
            procesarApuestasJugador(jugador, numeroGanador);
        }
    }

    private synchronized void procesarApuestasJugador(Jugador jugador, int numeroGanador) {
        for (Apuesta apuesta : jugador.getApuestas()) {
            switch (apuesta.getTipo()) {
                case NUMERO:
                    procesarApuestaNumero(jugador, apuesta, numeroGanador);
                    break;
                case PAR:
                    procesarApuestaParImpar(jugador, apuesta, numeroGanador, true); // true para Par
                    break;
                case IMPAR:
                    procesarApuestaParImpar(jugador, apuesta, numeroGanador, false); // false para Impar
                    break;
                case ROJO:
                    procesarApuestaColor(jugador, apuesta, numeroGanador, true); // true para Rojo
                    break;
                case NEGRO:
                    procesarApuestaColor(jugador, apuesta, numeroGanador, false); // false para Negro
                    break;
                case RANGO_INFERIOR:
                    procesarApuestaRango(jugador, apuesta, numeroGanador, true);
                    break;
                case RANGO_SUPERIOR:
                    procesarApuestaRango(jugador, apuesta, numeroGanador, false);
                    break;
                case PASAR:
                    System.out.println("Esperando al resultado...");
                    break;
                // Otros casos si es necesario...
            }
        }
    }

    private synchronized void procesarApuestaParImpar(Jugador jugador, Apuesta apuesta, int numeroGanador, boolean apostoPar) {
        // Verificamos que el número ganador no sea 0, ya que 0 generalmente no se considera ni par ni impar
        if (numeroGanador != 0) {
            boolean esPar = numeroGanador % 2 == 0;

            if (esPar == apostoPar) {
                // Ganó la apuesta
                int ganancia = apuesta.getMonto() * 2;
                jugador.ganarApuesta(ganancia);
            } else {
                // Perdió la apuesta
                jugador.perderApuesta();
            }
        } else {
            // Si el número ganador es 0, el jugador pierde la apuesta
            jugador.perderApuesta();
        }
    }

    private synchronized void procesarApuestaNumero(Jugador jugador, Apuesta apuesta, int numeroGanador) {
        if (apuesta.getNumeroEspecifico() == numeroGanador) {
            int ganancia = apuesta.getMonto() * 36; // Por ejemplo, si la paga es 36 a 1
            jugador.ganarApuesta(ganancia);
        } else {
            jugador.perderApuesta();
        }
    }

    private synchronized void procesarApuestaRango(Jugador jugador, Apuesta apuesta, int numeroGanador, boolean apostoSuperior) {
        if (numeroGanador != 0) {
            if (apostoSuperior) {
                if ((apuesta.getRangoSuperior().contains(numeroGanador))) {
                    int ganancia = apuesta.getMonto() * 2; // Suponiendo que la paga es 2 a 1
                    jugador.ganarApuesta(ganancia);
                } else {
                    jugador.perderApuesta();
                }
            } else {
                if ((apuesta.getRangoInferior().contains(numeroGanador))) {
                    int ganancia = apuesta.getMonto() * 2; // Suponiendo que la paga es 2 a 1
                    jugador.ganarApuesta(ganancia);
                } else {
                    jugador.perderApuesta();
                }
            }
        }

    }

    private synchronized void procesarApuestaColor(Jugador jugador, Apuesta apuesta, int numeroGanador, boolean apostoRojo) {
        if (numeroGanador != 0) { // Excluimos el 0, que generalmente no es ni rojo ni negro
            boolean esRojo = ruleta.esNumeroRojo(numeroGanador);
            boolean esNegro = ruleta.esNumeroNegro(numeroGanador);

            // Comprobamos si el jugador apostó al color correcto
            if ((apostoRojo && esRojo) || (!apostoRojo && esNegro)) {
                // Ganó la apuesta
                int ganancia = apuesta.getMonto() * 2; // Suponiendo que la paga es 2 a 1
                jugador.ganarApuesta(ganancia);
            } else {
                // Perdió la apuesta
                jugador.perderApuesta();
            }
        } else {
            // Si el número ganador es 0, el jugador pierde la apuesta
            jugador.perderApuesta();
        }
    }

    @Override
    public void run() {
        try {
            while (segundos > 0) {
                System.out.println("Tiempo restante: " + segundos + " segundos");
                Thread.sleep(10000);
                segundos -= 10;
            }
            for (Jugador jugador : jugadores) {
                if (jugador.esHumano()) {
                    jugador.realizarApuestaHumano(false);
                }
            }
            jugar();
            System.out.println("Si no se muestra el resultado introduce: no");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Otros métodos como getMensaje, numeroValido, etc.
}