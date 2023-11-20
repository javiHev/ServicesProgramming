import java.util.List;

public class Banca {
    private int dinero;
    private List<Jugadores>jugadores;

    public Banca(int dinero, List<Jugadores> jugadores) {
        this.dinero = dinero;
        this.jugadores = jugadores;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    public boolean aceptarApuesta(Jugadores jugador){
      if(jugadores.contains(jugador)){
          return jugador.getApuesta() <= jugador.getSaldo();

      }else{
          System.out.println("Este jugador no está jugando");
          return false;
      }
    }
    public void compruebaOpcion(){

    }
}
