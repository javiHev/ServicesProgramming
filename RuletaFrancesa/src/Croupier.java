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

   private void procesarApuestasJugador(Jugador jugador, int numeroGanador) {
      for (Apuesta apuesta : jugador.getApuestas()) {
         switch (apuesta.getTipo()) {
            case Jugador.TipoApuesta.NUMERO:
               procesarApuestaNumero(jugador, apuesta, numeroGanador);
               break;
            case Jugador.TipoApuesta.PAR_IMPAR:
               procesarApuestaParImpar(jugador, apuesta, numeroGanador);
               break;
            case Jugador.TipoApuesta.ROJO_NEGRO:
               procesarApuestaRojoNegro(jugador, apuesta, numeroGanador);
               break;
            case Jugador.TipoApuesta.RANGO:
               procesarApuestaRango(jugador, apuesta, numeroGanador);
               break;
            // Otros tipos de apuestas...
         }
      }
   }

   private void procesarApuestaParImpar(Jugador jugador, Apuesta apuesta, int numeroGanador) {
      // Verificamos que el número ganador no sea 0, ya que 0 generalmente no se considera ni par ni impar
      if (numeroGanador != 0) {
         boolean esPar = numeroGanador % 2 == 0;
         boolean apostoPar = apuesta.getTipoApuestaParImpar(); // Suponemos que este método devuelve true si apostó a par, false si apostó a impar

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

   private void procesarApuestaNumero(Jugador jugador, Apuesta apuesta, int numeroGanador) {
      if (apuesta.getNumeroEspecifico() == numeroGanador) {
         int ganancia = apuesta.getMonto() * 36; // Por ejemplo, si la paga es 36 a 1
         jugador.ganarApuesta(ganancia);
      } else {
         jugador.perderApuesta();
      }
   }
   private void procesarApuestaRango(Jugador jugador, Apuesta apuesta, int numeroGanador) {
      if ((apuesta.getRangoInferior().contains(numeroGanador)) && (numeroGanador <= apuesta.getRangoSuperior())) {
         int ganancia = apuesta.getMonto() * 2; // Suponiendo que la paga es 2 a 1
         jugador.ganarApuesta(ganancia);
      } else {
         jugador.perderApuesta();
      }
   }

   @Override
   public void run() {
      try {
         while (segundos > 0) {
            System.out.println("Tiempo restante: " + segundos + " segundos");
            Thread.sleep(1000);
            segundos--;
         }
         jugar();
      } catch (InterruptedException e) {
         e.printStackTrace();
      }
   }

   // Otros métodos como getMensaje, numeroValido, etc.
}