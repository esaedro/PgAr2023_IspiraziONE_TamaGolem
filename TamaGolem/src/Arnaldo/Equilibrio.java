package Arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

public class Equilibrio {
    public static final int N = 5;
    private static int[][] tabellaEquilibrio = new int[N][N];
    private static int[] valori = { -1, -1, -1, -1, -1, -1, -1, -1, 1, 2, 3, 4, 5, 6, 7, 8};

/*     public static void main(String[] args) {

        generaEquilibrio();
        mostraEquilibrio();
    } */

    /**
     * Genera l'equilibrio dello scontro attuale
     */
    public static void generaEquilibrio() {
        int sommaRiga, sommaColonna, valoreEstratto, i, j;

        cicloDellaRiga: for (i = 0; i < N - 1; i++) {
            sommaRiga = sommaColonna = 0;

            // Somma i numeri sulla riga e sulla colonna che precedono 'i'
            for (int k = 0; k < i; k++) {
                sommaRiga += tabellaEquilibrio[i][k] > 0 ? tabellaEquilibrio[i][k] : 0;
                sommaColonna += tabellaEquilibrio[k][i] > 0 ? tabellaEquilibrio[k][i] : 0;
            }

            // Genera i numeri di riga 'i' e colonna 'i' fino alla penultima posizione
            for (j = i + 1; j < N - 1; j++) {
                valoreEstratto = valori[EstrazioneCasuale.estraiIntero(0, valori.length - 1)];

                if (valoreEstratto > 0) {
                    tabellaEquilibrio[i][j] = valoreEstratto;
                    tabellaEquilibrio[j][i] = valori[0];
                    sommaRiga += tabellaEquilibrio[i][j];
                } else {
                    tabellaEquilibrio[i][j] = valori[0];
                    tabellaEquilibrio[j][i] = valori[EstrazioneCasuale.estraiIntero(valori.length / 2,
                            valori.length - 1)];
                    sommaColonna += tabellaEquilibrio[j][i];
                }
            }

            while (sommaRiga == sommaColonna) {
                // Se si trova alla penultima riga e la somma della riga è già uguale alla somma della colonna, rigenera le terzultime riga e colonna
                if (i == j - 1) {
                    i -= 2;
                    continue cicloDellaRiga;
                }

                // Controlla dove è l'utlimo numero inserito e lo rigenera
                if (tabellaEquilibrio[i][j - 1] > 0) {
                    sommaRiga -= tabellaEquilibrio[i][j - 1];
                    tabellaEquilibrio[i][j - 1] = valori[EstrazioneCasuale.estraiIntero(valori.length / 2,
                            valori.length - 1)];
                    sommaRiga += tabellaEquilibrio[i][j - 1];
                } else {
                    sommaColonna -= tabellaEquilibrio[j - 1][i];
                    tabellaEquilibrio[j - 1][i] = valori[EstrazioneCasuale.estraiIntero(valori.length / 2,
                            valori.length - 1)];
                    sommaColonna += tabellaEquilibrio[j - 1][i];
                }
            }

            // Calcola l'ultimo numero da inserire nella riga (o colonna) per rispettare l'ugualianza delle somme
            if (sommaRiga > sommaColonna) {
                tabellaEquilibrio[i][j] = valori[0];
                tabellaEquilibrio[j][i] = sommaRiga - sommaColonna;
            } else {
                tabellaEquilibrio[i][j] = sommaColonna - sommaRiga;
                tabellaEquilibrio[j][i] = valori[0];
            }
        }
    }

    /**
     * Mostra a video la tabella delle adiacenze che rappresenta il grafo pesato dell'equilibrio
     */
    public static void mostraEquilibrio() {

        for (int[] riga : tabellaEquilibrio) {
            System.out.println();
            for (int elemento : riga) {
                if (elemento == -1) {
                    System.out.print("  X");
                }
                else {
                    System.out.print(String.format("%3d", elemento));
                }
            }
        }

        System.out.println();

    }

}
