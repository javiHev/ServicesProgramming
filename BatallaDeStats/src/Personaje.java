
    public class Personaje extends Thread {
        private String nombre;
        private int totalDaño;
        private Zona zona;
        private Item espada;
        private Item casco;
        private Item peto;

        public Personaje(String nombre, Zona zona) {
            this.nombre = nombre;
            this.totalDaño = 0;
            this.zona = zona;
            this.espada = null;
            this.casco = null;
            this.peto = null;
        }

        public int getTotalDaño() {
            return totalDaño;
        }

        public String getNombre() {
            return nombre;
        }

        public Zona getZona() {
            return zona;
        }
        public String getNombreZona(){
            return zona != null ? zona.getNombre() : "Desconocida";
        }

        public synchronized void obtenerItem(Item item) {
            if (item != null && !item.isObtenido()) {
                if (item.getTipo().equals("Espada")) {
                    cambiarItem(espada, item);
                    espada = item;
                } else if (item.getTipo().equals("Peto")) {
                    cambiarItem(peto, item);
                    peto = item;
                } else if (item.getTipo().equals("Casco")) {
                    cambiarItem(casco, item);
                    casco = item;
                }
                totalDaño = calcularDañoTotal();
                item.setObtenido(true);
                String respuesta=(item.getTipo().equals("Espada"))? nombre + " ha obtenido una " + item.getTipo() +" con daño "+item.getDaño() : nombre + " ha obtenido un " + item.getTipo() +" con daño "+item.getDaño();
                System.out.println(respuesta);
            }
        }
        private void  cambiarItem(Item itemAntiguo, Item nuevoItem){
            if (itemAntiguo != null) {
                totalDaño -= itemAntiguo.getDaño();
                System.out.println(nombre + " ha dejado su " + itemAntiguo.getTipo());
            }
        }
        private int calcularDañoTotal(){
            int total=0;
            if (espada != null) total += espada.getDaño();
            if (peto != null) total += peto.getDaño();
            if (casco != null) total += casco.getDaño();
            return total;
        }
        @Override
        public void run() {
            while (true) {
                Item item = zona.getRandomItem();
                obtenerItem(item);
                try {
                    Thread.sleep(2000); // Los personajes intentan equiparse cada 2 segundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }


