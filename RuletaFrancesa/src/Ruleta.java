import java.util.*;

public class Ruleta extends Thread {
    Scanner scanner;
    Random random = new Random();
    List<Integer> negros;
    List<Integer> rojos;
    int numeroGanador;
    private List<Jugadores>jugadores;

    public Ruleta(List<Jugadores>jugadores) {
        this.jugadores=jugadores;
        this.numeroGanador = random.nextInt(36);
        this.negros = Arrays.asList(2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35);
        List<Integer> numerosRojos = new ArrayList<>();
        for (int i = 1; i <= 36; i++) {
            if (!negros.contains(i)) {
                numerosRojos.add(i);
            }
        }
    }

    public int getNumeroGanador() {
        return numeroGanador;
    }

    public synchronized void opciones() {
        System.out.println("Menú de Opciones:");
        System.out.println("1. 0-36");
        System.out.println("2. Par/Impar");
        System.out.println("3. Negro/Rojo");
        System.out.println("4. 1-18/19-36");
        System.out.println("5. Pasar");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        int opcion= scanner.nextInt();
        switch (opcion) {
            case 1:
                System.out.println("Usted seleccionó 0-36");
                comprobar(jugadores,opcion);
                break;
            case 2:
                System.out.println("Usted seleccionó Par/Impar");
                break;
            case 3:
                System.out.println("Usted seleccionó Negro/Rojo");
                break;
            case 4:
                System.out.println("Usted seleccionó 1-18/19-36");
                break;
            case 5:
                System.out.println("Usted seleccionó Pasar");
                break;
            case 0:
                System.out.println("Saliendo del programa. ¡Hasta luego!");
                break;
            default:
                System.out.println("Opción no válida. Inténtelo de nuevo.");
        }


    }
    public void comprobar(List<Jugadores>jugadores,int opcion){
        if(opcion==1){

        }
    }



}
