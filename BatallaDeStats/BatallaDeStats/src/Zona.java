import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Zona {
    //***************************************************************************
    //          DECLARACION DE VARIBLES
    //***************************************************************************
    List<Item> items;
    String nombre;

    public Zona(String nombre) {
        this.items = new ArrayList<>();
        this.nombre=nombre;
    }
    //***************************************************************************
    //          METODOS DE LA CLASE ZONA
    //***************************************************************************

    public void addItem(Item item){
       items.add(item);
    }

    public String getNombre() {
        return nombre;
    }

    public synchronized Item getRandomItem() {
        if (items.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(items.size());
        return items.remove(randomIndex);
    }

}
