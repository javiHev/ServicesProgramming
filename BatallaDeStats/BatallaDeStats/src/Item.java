public class Item {
    //***************************************************************************
    //          DECLARACION DE VARIBLES
    //***************************************************************************
    private String tipo;
    private int daño;
    private boolean obtenido;

    public Item(String tipo, int daño) {
        this.tipo = tipo;
        this.daño = daño;
    }
    //***************************************************************************
    //          METODOS DE LA CLASE ITEM
    //***************************************************************************

    public boolean isObtenido() {
        return obtenido;
    }

    public void setObtenido(boolean obtenido) {
        this.obtenido = obtenido;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDaño() {
        return daño;
    }
}
