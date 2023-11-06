import javax.imageio.event.IIOReadProgressListener;
import java.util.ArrayList;
import java.util.List;

public class Zona {
    int idZona;
    String nombreZona;
    List<Personajes> personajes=new ArrayList<>();
    List<Items> items=new ArrayList<>();

    public Zona(int idZona, String nombreZona) {
        this.idZona = idZona;
        this.nombreZona = nombreZona;
    }
    
}
