
public class Main {
    public static void main(String[] args) throws InterruptedException {

        Coche coche1 = new Coche("Mclaren", 170);
        Coche coche2 = new Coche("BMW", 200);
        Coche coche3 = new Coche("Masseratti", 300);
        coche1.start();
        coche2.start();
        coche3.start();

        try {
            coche1.join();
            coche2.join();
            coche3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Determinar el ganador y los puestos
        Coche[] coches = {coche1, coche2, coche3};
        ordenarCochesPorDistancia(coches);

        System.out.println("Resultados de la carrera:");
        for (int i = 0; i < coches.length; i++) {
            System.out.println("Puesto " + (i + 1) + ": " + coches[i].getNombre() + " - Distancia recorrida: " + coches[i].getDistanciaRecorrida() + " metros");
        }
    }

    //Bubble Sort
    private static void ordenarCochesPorDistancia(Coche[] coches) {
        for (int i = 0; i < coches.length - 1; i++) {
            for (int j = i + 1; j < coches.length; j++) {
                if (coches[i].getDistanciaRecorrida() < coches[j].getDistanciaRecorrida()) {
                    //variable temporal para sobreescribir los datos y cambiar un coche por otro
                    Coche temp = coches[i];
                    coches[i] = coches[j];
                    coches[j] = temp;
                }
            }
        }
    }


}
