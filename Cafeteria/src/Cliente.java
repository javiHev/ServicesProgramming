public class Cliente extends Thread {
    private String nombre;
    private int tiempoEspera;

    public Cliente(String nombre, int tiempoEspera) {
        this.nombre = nombre;
        this.tiempoEspera = tiempoEspera;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempoEspera() {
        return tiempoEspera;
    }

    public void run() {
        System.out.println(nombre + " entra a la cafetería y espera " + tiempoEspera + " segundos.");
        long startTime = System.currentTimeMillis();
        synchronized (Cafeteria.clientesEnEspera) {
            Cafeteria.clientesEnEspera.add(this);
        }
        while (true) {
            if (Cafeteria.baristaDisponible()) {
                try {
                    Cafeteria.prepararCafe(this, startTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            }
            if (System.currentTimeMillis() - startTime >= tiempoEspera * 1000) {
                synchronized (Cafeteria.clientesEnEspera) {
                    if (Cafeteria.clientesEnEspera.contains(this)) {
                        Cafeteria.clientesEnEspera.remove(this);
                        System.out.println(nombre + " se va de la cafetería sin recibir su café.");
                        break;
                    }
                }
            }
        }
    }
}
