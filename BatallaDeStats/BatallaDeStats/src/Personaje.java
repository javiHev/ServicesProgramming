
    public class Personaje extends Thread {
    //***************************************************************************
        //          DECLARACION DE VARIBLES
    //***************************************************************************
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
        //***************************************************************************
        //          METODOS DE LA CLASE PERSONAJE
        //***************************************************************************

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
        /*
        * Metodo que recibe un Item, comprueba que tipo es y recoge el objeto, si el objeto es mejor que el que tiene
        * lo cambia.
        */
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
        /*
        * Este metodo decide si cambiar o no un item dependiendo de su daño
        * Recibe dos parametros,el item que tenía y el item que se ha encontrado*/
        private void  cambiarItem(Item itemAntiguo, Item nuevoItem){
            if(itemAntiguo==null){
                itemAntiguo=nuevoItem;
            }
            if(itemAntiguo.getDaño()<= nuevoItem.getDaño()) {
                if (itemAntiguo != null) {
                    totalDaño -= itemAntiguo.getDaño();
                    System.out.println(nombre + " ha dejado su " + itemAntiguo.getTipo());
                }
            }else{
                if (itemAntiguo != null) {
                    System.out.println(nombre + " se ha encontrado un Item pero el suyo hace más daño, se queda con: " + itemAntiguo.getTipo());
                }
            }
        }
        /*
        *Este metodo calula el daño total de cada Personaje
        * */
        private int calcularDañoTotal(){
            int total=0;
            //Compruebo que armas tiene el personaje
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


