package Arnaldo;

public class Pietra {

    /**
     * Numero di pietre per ogni tamagolem
     */
    public static final int P = (int)Math.ceil((double)(Equilibrio.getN() + 1) / 3.0) + 1;

    private Elemento elemento;

    public Pietra (Elemento elemento) {
        this.elemento = elemento;
    }

    public Elemento getElemento() {
        return elemento;
    }
}
