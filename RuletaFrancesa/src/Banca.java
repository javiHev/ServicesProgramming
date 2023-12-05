public class Banca {
    private int dinero;
    public Banca(int dineroInicial) {
        this.dinero = dineroInicial;
    }
    public int getDineroBanca(){
        return dinero;
    }
    public void aumentarDinero(int cantidad) {
        dinero += cantidad;
    }

    public void reducirDinero(int cantidad) {
        dinero -= cantidad;
    }

}
