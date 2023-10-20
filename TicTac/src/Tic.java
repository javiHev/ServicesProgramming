public class Tic extends Thread {
    //Variables
    private int hilo;

    public Tic(int hilo) {
        this.hilo = hilo;
    }
    public void run(){
        while(true){
            System.out.println("TIC");
            try {
                sleep(1000);
                this.yield();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
