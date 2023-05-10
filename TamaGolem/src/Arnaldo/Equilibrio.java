package Arnaldo;

import it.unibs.fp.mylib.EstrazioneCasuale;

/**
 * Classe static per gestire l'equilibrio decgli elementi
 */
public class Equilibrio {

    /**
     * Numero di elementi
     */
    private static int N;
    private static final int[] valori = {-1, -1, -1, -1, -1, -1, -1, -1, 1, 2, 3, 4, 5, 6, 7, 8};
    private static int[][] tabellaEquilibrio;
    private static int potenzaMassima;

    public static int getN() {
        return N;
    }

    public static int[][] getTabellaEquilibrio() {
        return tabellaEquilibrio;
    }

    public static int getPotenzaMassima() {
        return potenzaMassima;
    }

    public static void setN(int n) {
        N = n;
    }

    /**
     * Genera l'equilibrio dello scontro attuale
     */
    public static void generaEquilibrio() {
        int sommaRiga, sommaColonna, valoreEstratto, i, j;
        tabellaEquilibrio = new int[N][N];

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

                // Controlla dove è l'ultimo numero inserito e lo rigenera
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
        calcolaPotenzaMassima();
    }

    /**
     * Mostra a video la tabella delle adiacenze che rappresenta il grafo pesato dell'equilibrio
     */
    public static void mostraEquilibrio() {

        for (int i = 0; i < N ;i ++) {
            System.out.print("\n" + String.format("%-10s", Elemento.values()[i].toString()));
            for (int elemento : tabellaEquilibrio[i]) {
                if (elemento < 0) {
                    System.out.print("  X");
                }
                else {
                    System.out.print(String.format("%3d", elemento));
                }
            }
        }
        System.out.println();
    }

    /**
     * Trova il valore di potenza massimo all'interno della tabella dell'equilibrio
     */
    public static void calcolaPotenzaMassima() {
        int max = 0;
        
        for (int[] riga : tabellaEquilibrio) {
            for (int potenza : riga) {
                max = potenza > max ? potenza : max;
            }
        }
        potenzaMassima = max;
    }
}