public class Barista extends Thread{
/*    public void prepararCafe(Cliente cliente){
        System.out.println("Barista está preparando café para " + cliente.getNombre());
        try {
            Thread.sleep(2000); // Simula el tiempo de preparación del café.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Barista ha terminado de preparar café para " + cliente.getNombre());

    }*/
    @Override
    public void run(){
        while (true) {
            Cliente cliente;
            synchronized (Cafeteria.clientesEnEspera) {
                if (Cafeteria.clientesEnEspera.isEmpty()) {
                    continue;
                }
                cliente = Cafeteria.clientesEnEspera.get(0);
            }
            try {
                Cafeteria.prepararCafe(cliente, System.currentTimeMillis());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
