public class Tic extends Thread {
    //Variables
    private Cola cola;


    public Tic(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while(true) {
            cola.get("Tic");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
