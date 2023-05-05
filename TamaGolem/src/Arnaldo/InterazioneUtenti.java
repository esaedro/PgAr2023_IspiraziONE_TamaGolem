package Arnaldo;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class InterazioneUtenti {

    private static final int MIN_ELEMENTI = 3;
    private static final int MAX_ELEMENTI = 10;

    /**
     * Chiede al giocarore di selezionare quali pietre elementali vuole inserire nella scorta 
     */
    public static Pietra selezionaPietra(Giocatore giocatore) {
        String[] listaPietre = new String[Equilibrio.getN()];
        Pietra pietraSelezionata;
        int[] contatori = new int[Equilibrio.getN()];

        for (int i = 0; i < Equilibrio.getN(); i++) {
            for (Pietra pietra : Scontro.getScortaDiPietre()) {
                if (pietra.getElemento() == Elemento.values()[i]) {
                    contatori[i]++;
                }
            }
            listaPietre[i] = String.format("%-10s %d rimasti", Elemento.values()[i], contatori[i]);
        }

        MyMenu menu = new MyMenu(giocatore.getNome() + ", " + Frasi.TITOLO_MENU_SELEZIONE_PIETRA, listaPietre);

        int indicePietraSelezionata = 0;
        int indiceElementoSelezionato = menu.scegli();

        for (int i = 0; i < indiceElementoSelezionato - 1; i++) {
            indicePietraSelezionata += contatori[i];
        }

        pietraSelezionata = Scontro.getScortaDiPietre().get(indicePietraSelezionata);

        return pietraSelezionata;
    }

    /**
     * Chiede ai giocatori di inserire il loro nome
     */
    public static Giocatore inserimentoGiocatore(int numeroGiocatore) {
        String nome = InputDati.leggiStringaNonVuota(Frasi.INPUT_GIOCATORE + numeroGiocatore + ": ", Frasi.ERRORE_INPUT_GIOCATORE);
        return new Giocatore(nome);
    }

    /**
     * Chiede al giocatore di selezionare il numero di elementi da generare per l'Equilibrio
     */
    public static int selezionaNumeroDiElementi() {
        return InputDati.leggiInteroMinMax(Frasi.RICHIESTA_NUMERO_ELEMENTI, MIN_ELEMENTI, MAX_ELEMENTI, Frasi.ERRORE_NUMERO_ELEMENTI);
    }

    /**
     * Chiede all'utente e restituisce true se si vuole giocare una nuova partita
     */
    public static boolean continuaPartita() {
        return InputDati.conferma(Frasi.CONFERMA_NUOVO_SCONTRO);
    }

    /**
     * Termina lo scontro stampando il vincitore e l'equilibrio 
     */
    public static void terminaScontro(Giocatore vincitore) {
        System.out.println(vincitore.getNome() + Frasi.DICHIARAZIONE_VINCITORE);
        System.out.println(Frasi.RIVELAZIONE_EQUILIBRIO);
        Equilibrio.mostraEquilibrio();
    }
    
    public static void morteTamagolem(Giocatore giocatore) {
        System.out.println(Frasi.MESSAGGIO_GOLEM_SCONFITTO + giocatore.getNome());
        System.out.println(Frasi.GOLEM_RIMASTI + (Giocatore.G - giocatore.getTamaGolemEliminati()));
    }

    /**
     * Stampa a video il danno subito dal golem perdente
     */
    public static void mostraDanno(int danno, Giocatore giocatore) {
        System.out.println("Il golem di "+ giocatore.getNome() + " subisce " + danno + " danni");
    }

    public static void sceltaPietre (Giocatore giocatore1, Giocatore giocatore2) {
        System.out.println("\n" + giocatore1.getNome() + " selezioni un totale di " + Pietra.P + " pietre");
        giocatore1.prelevaPietre();
        boolean primoCiclo = true;
        do {
            if (!primoCiclo && giocatore1.getTamaGolemAttuale().getSetdiPietre().equals(giocatore2.getTamaGolemAttuale().getSetdiPietre())) {
                System.out.println(Frasi.ERRORE_PIETRE);
            }
            System.out.println("\n" + giocatore2.getNome() + " selezioni " + Pietra.P + " pietre");
            giocatore2.prelevaPietre();
            primoCiclo = false;
        } while (giocatore1.getTamaGolemAttuale().getSetdiPietre().equals(giocatore2.getTamaGolemAttuale().getSetdiPietre()));
    }

    /*public static void stampaCostanti() {
        System.out.println("\nN = " + Equilibrio.getN());
        System.out.println("P = " + Pietra.P);
        System.out.println("G = " + Giocatore.G);
        System.out.println("S = " + Scontro.S);
    }*/

}
