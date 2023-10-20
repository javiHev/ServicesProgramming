import java.util.Random;

class Coche extends Thread {
    private String nombre;
    private int velocidadMaxima;
    private int distanciaRecorrida;

    public Coche(String nombre, int velocidadMaxima) {
        this.nombre = nombre;
        this.velocidadMaxima = velocidadMaxima;
        this.distanciaRecorrida = 0;
    }

    public String getNombre() {
        return nombre;
    }



    @Override
    public void run() {
        Random random = new Random();
        int vuelta = 0;
        while (vuelta < 3) { // La carrera tiene 3 vueltas
            int velocidadActual = random.nextInt(velocidadMaxima + 1);
            distanciaRecorrida += velocidadActual;
            System.out.println(nombre + " ha recorrido " + distanciaRecorrida + " metros en la vuelta " + (vuelta + 1));
            vuelta++;

            try {
                Thread.sleep(1000); // Esperar un segundo entre vueltas
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println("___________________________________________________________________________________");
        System.out.println(nombre + " ha terminado la carrera.");
    }

    public int getDistanciaRecorrida() {
        return distanciaRecorrida;
    }
}




