
public class Personajes {
    Random random=new Random();
    String nombre;
    int daño;

    public Personajes(String nombre) {
        this.nombre = nombre;

    }

    public String getNombrePersonaje() {
        return nombre;
    }

    public int getDañoPersonaje() {
        return daño;
    }
}
