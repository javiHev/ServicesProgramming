public class Tac extends Thread {
    //Variables
    private Cola cola;


    public Tac(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        while(true) {
            cola.get("Tac");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
