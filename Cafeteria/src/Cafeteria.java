import java.util.ArrayList;

public class Cafeteria {
    public static ArrayList<Cliente> clientesEnEspera = new ArrayList<>();

    public static void main(String[] args) {
        Barista barista1=new Barista();
        Barista barista2=new Barista();
        barista1.start();
        barista2.start();
        Cliente cliente1=new Cliente("Martita",10);
        Cliente cliente2=new Cliente("No",1);
        Cliente cliente3=new Cliente("Que no",2);
        cliente1.start();
        cliente2.start();
        cliente3.start();
    }
    public static synchronized boolean baristaDisponible(){
        return true;
    }
    public static void prepararCafe(Cliente cliente,long startTime) throws InterruptedException {
        System.out.println("Barista está preparando café para "+cliente.getNombre()+".");
        try {
            Thread.sleep(2000); // Simula el tiempo de preparación del café.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Barista ha terminado de preparar café para " + cliente.getNombre() + " (tiempo transcurrido: " +
                (System.currentTimeMillis() - startTime) + " ms)");
        synchronized (Cafeteria.clientesEnEspera) {
            Cafeteria.clientesEnEspera.remove(cliente);
        }

    }

}