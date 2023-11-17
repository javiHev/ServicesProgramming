import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {
    private static final int DAÑO_OBJETIVO = 120;  //Declaramos un daño objetivo para determinar el final del juego.

    public static void main(String[] args) {
        Zona zona1 = new Zona("Pisos picados");
        Zona zona2 = new Zona("Caserio Colesterol");
        List<Personaje> personajes = new ArrayList<>();
        personajes.add(new Personaje("Javi", zona1));
        personajes.add(new Personaje("TheRealBraisamei", zona2));
        personajes.add(new Personaje("Hugo", zona2));
        personajes.add(new Personaje("Adri", zona1));

        Thread itemThread1 = new Thread(() -> {
            while (true) {
                zona1.addItem(new Item("Espada", generarDañoAleatorio()));
                zona1.addItem(new Item("Peto", generarDañoAleatorio()));
                zona1.addItem(new Item("Casco", generarDañoAleatorio()));
                try {
                    Thread.sleep(5000); // Aparece un ítem cada 5 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread itemThread2 = new Thread(() -> {
            while (true) {
                zona2.addItem(new Item("Espada", generarDañoAleatorio()));
                zona2.addItem(new Item("Casco", generarDañoAleatorio()));
                zona2.addItem(new Item("Peto", generarDañoAleatorio()));
                try {
                    Thread.sleep(7000); // Aparece un ítem cada 7 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        // Iniciar los hilos de los personajes
        for (Personaje character : personajes) {
            character.start();
        }

        // Iniciar los hilos para la aparición de ítems
        itemThread1.start();
        itemThread2.start();

        // Imprimir la lista ordenada de personajes por su daño total
        while (true) {
            try {
                Thread.sleep(10000); // Actualizar y mostrar la lista cada 10 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Collections.sort(personajes, (c1, c2) -> c2.getTotalDaño() - c1.getTotalDaño());

            System.out.println("\nLista de personajes ordenada por daño total:");
            for (Personaje character : personajes) {
                if (character.getTotalDaño() < DAÑO_OBJETIVO)
                    System.out.println(character.getNombre() + " - Daño total: " + character.getTotalDaño() + " - Zona: " + character.getNombreZona());


                else if (character.getTotalDaño() >= DAÑO_OBJETIVO) {

                    System.out.println("¡El juego ha terminado! " + character.getNombre() + " ha alcanzado el daño objetivo de " + DAÑO_OBJETIVO + ".");
                    System.exit(0);
                }


            }
        }


    }

    private static int generarDañoAleatorio() {
        Random random = new Random();
        return random.nextInt(50) + 1; // Número aleatorio entre 1 y 50
    }
}
