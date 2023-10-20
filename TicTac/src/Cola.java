public class Cola {
    private boolean ticTurn = true;

    public synchronized void get(String message) {
        while ((message.equals("Tic") && !ticTurn) || (message.equals("Tac") && ticTurn)) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(message);
        ticTurn = !ticTurn;
        notifyAll();
    }
}
