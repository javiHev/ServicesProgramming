public class Tac extends Thread {
    //Variables
    private int hilo;

    public Tac (int hilo) {
        this.hilo = hilo;
    }
    public void run(){
        while(true){
            System.out.println("TAC");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
