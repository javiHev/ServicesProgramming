public class Main {
    public static void main(String[] args) {
        Cola cola = new Cola();
        Tic tic = new Tic(cola);
        Tac tac = new Tac(cola);

        tic.start();
        tac.start();
    }
}