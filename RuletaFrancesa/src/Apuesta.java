import java.util.ArrayList;
import java.util.List;

public class Apuesta {

    private final int monto;
    private final Jugador.TipoApuesta tipo;
    private Integer numeroEspecifico; // Solo para apuestas a número específico

    // Constructor para tipos de apuesta que no requieren un número específico
    public Apuesta(int monto, Jugador.TipoApuesta tipo) {
        this.monto = monto;
        this.tipo = tipo;
    }

    // Constructor para apuestas a un número específico
    public Apuesta(int monto, Jugador.TipoApuesta tipo, int numeroEspecifico) {
        this(monto, tipo);
        if (tipo != Jugador.TipoApuesta.NUMERO) {
            throw new IllegalArgumentException("El número específico solo es válido para apuestas de tipo NUMERO");
        }
        this.numeroEspecifico = numeroEspecifico;
    }

    public Jugador.TipoApuesta getTipo() {
        return tipo;
    }

    public int getNumeroEspecifico() {
        return numeroEspecifico;
    }

    public int getMonto() {
        return monto;
    }

    public static List<Integer> getRangoInferior() {
        List<Integer> rangoInferior = new ArrayList<>();
        for (int i = 1; i <= 18; i++) {
            rangoInferior.add(i);
        }
        return rangoInferior;
    }

    public static List<Integer> getRangoSuperior() {
        List<Integer> rangoSuperior = new ArrayList<>();
        for (int i = 19; i <= 36; i++) {
            rangoSuperior.add(i);
        }
        return rangoSuperior;
    }
}
