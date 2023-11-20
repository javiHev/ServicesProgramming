public class Jugadores extends Thread {
    private int saldo;
    private int apuesta;
    private String tipoApuesta;


    public Jugadores(int saldo, int apuesta) {
        this.saldo = saldo;
        this.apuesta = apuesta;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getApuesta() {
        return apuesta;
    }

    public void run(){
        while(true){

            try {
                Thread.sleep(30000); // Los jugadores tienen 30 segundos para apostar
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
