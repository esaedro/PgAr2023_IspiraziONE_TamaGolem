package Arnaldo;

public class Pietra {

    /**
     * Numero di pietre per ogni tamagolem
     */
    public static final int P = 1 + (int)Math.ceil((Equilibrio.getN() + 1) / 3.0);

    private Elemento elemento;

    public Pietra (Elemento elemento) {
        this.elemento = elemento;
    }

    public Elemento getElemento() {
        return elemento;
    }
}
