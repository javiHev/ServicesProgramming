import java.util.Random;
public class Items {
    String tipo;
    int daño;
    Random random=new Random();

    public Items(String tipo) {
        this.tipo = tipo;
        this.daño=random.nextInt(101);
    }
    
}
