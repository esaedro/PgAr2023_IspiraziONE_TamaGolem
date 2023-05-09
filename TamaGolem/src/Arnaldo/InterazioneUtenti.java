package Arnaldo;

import java.util.ArrayList;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class InterazioneUtenti {

    private static final int MIN_ELEMENTI = 3;
    private static final int MAX_ELEMENTI = 10;

    /**
     * Chiede al giocatore di selezionare quali pietre elementali vuole inserire nella scorta 
     */
    public static Pietra selezionaPietra(Giocatore giocatore, ArrayList<Pietra> scorta) {
        String[] listaPietre = new String[Equilibrio.getN()];
        Pietra pietraSelezionata;
        int[] contatori = new int[Equilibrio.getN()];

        for (int i = 0; i < Equilibrio.getN(); i++) {
            for (Pietra pietra : scorta) {
                if (pietra.getElemento() == Elemento.values()[i]) {
                    contatori[i]++;
                }
            }
            listaPietre[i] = String.format("%-10s %d rimasti", Elemento.values()[i], contatori[i]);
        }

        MyMenu menu = new MyMenu(giocatore.getNome() + ", " + Frasi.TITOLO_MENU_SELEZIONE_PIETRA, listaPietre);

        int indicePietraSelezionata = 0;
        int indiceElementoSelezionato = menu.scegli() - 1;

        while (indiceElementoSelezionato == -1) {
            System.out.println(Frasi.MESSAGGIO_TENTATA_USCITA);
            indiceElementoSelezionato = menu.scegli() - 1;
        }
        
        while (contatori[indiceElementoSelezionato] == 0) {
            System.out.println(Frasi.MESSAGGIO_PIETRE_FINITE);
            indiceElementoSelezionato = menu.scegli() - 1;
        }

        for (int i = 0; i < indiceElementoSelezionato; indicePietraSelezionata += contatori[i], i++);

        pietraSelezionata = scorta.get(indicePietraSelezionata);

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
        System.out.println(Frasi.GOLEM_RIMASTI + (Giocatore.getG() - giocatore.getTamaGolemEliminati()));
    }

    /**
     * Stampa a video il danno subito dal golem perdente
     */
    public static void mostraDanno(int danno, Giocatore giocatore) {
        System.out.println("Il golem di "+ giocatore.getNome() + " subisce " + danno + " danni");
    }

/*     public static void sceltaPietre(Giocatore giocatore1, Giocatore giocatore2) {
        int pietrePerGolem = Pietra.getP(), contatore = 0;
        Pietra[] pietre1, pietre2;
        System.out.println("\n" + giocatore1.getNome() + ", seleziona un totale di " + pietrePerGolem + " pietre");
        giocatore1.prelevaPietre();
        System.out.println("\n" + giocatore2.getNome() + ", seleziona un totale di " + pietrePerGolem + " pietre");
        giocatore2.prelevaPietre();

        for (int i = 0; i < pietrePerGolem; i++) {
            pietre1 = giocatore1.getTamaGolemAttuale().getSetDiPietre().toArray(new Pietra[pietrePerGolem]);
            pietre2 = giocatore2.getTamaGolemAttuale().getSetDiPietre().toArray(new Pietra[pietrePerGolem]);

            contatore += (pietre1[i].getElemento().equals(pietre2[i].getElemento())) ? 1 : 0;
        }

        while (contatore == pietrePerGolem) {
            System.out.println("\nI set di pietre selezionati dai due giocatori sono uguali, si creerebbe un loop infinito.\nÈ necessario riselezionare i set per continuare");
            sceltaPietre(giocatore1, giocatore2);
        }
    } */

    public static void inizializzaVariabiliPartita(int n) {
        Equilibrio.setN(n);
        Pietra.setP(1 + (int)Math.ceil((n + 1) / 3.0));
        Giocatore.setG((int)Math.ceil((n - 1) * (n - 2) / (2.0*Pietra.getP())));
        Scontro.setS((int)(n * Math.ceil((2.0*Giocatore.getG()*Pietra.getP())/ n)));
    }

    public static void stampaCostanti() {
        System.out.println("\nN = " + Equilibrio.getN());
        System.out.println("P = " + Pietra.getP());
        System.out.println("G = " + Giocatore.getG());
        System.out.println("S = " + Scontro.getS());
    }

}
