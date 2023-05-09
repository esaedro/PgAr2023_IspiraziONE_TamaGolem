package Arnaldo;

public class Pietra {

    /**
     * Numero di pietre per ogni tamagolem
     */
    private static int P;

    private Elemento elemento;

    public Pietra(Elemento elemento) {
        this.elemento = elemento;
    }

    public static int getP() {
        return P;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public static void setP(int p) {
        P = p;
    }
}
