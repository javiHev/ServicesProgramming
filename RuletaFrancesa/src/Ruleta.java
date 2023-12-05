import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Ruleta {

    private Random random = new Random();
    private List<Integer> negros;
    private List<Integer> rojos;
    private int numeroGanador;

    public Ruleta() {
        negros = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);
        rojos = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            if (!negros.contains(i)) {
                rojos.add(i);
            }
        }
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public void girar() {
        numeroGanador = random.nextInt(37); // Números de 0 a 36
        System.out.println("La ruleta se ha detenido en el número: " + numeroGanador);
    }

    // Métodos adicionales si necesitas verificar si un número es rojo o negro
    public boolean esNumeroRojo(int numero) {
        return rojos.contains(numero);
    }

    public boolean esNumeroNegro(int numero) {
        return negros.contains(numero);
    }
}
