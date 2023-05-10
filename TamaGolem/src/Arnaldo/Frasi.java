package Arnaldo;

import it.unibs.fp.mylib.BelleStringhe;

/**
 * Classe static contenente le costanti stringa necessarie
 */
public class Frasi {

    public static final String TITOLO_MENU_SELEZIONE_PIETRA = "scegli una pietra";
    public static final String DICHIARAZIONE_VINCITORE = " ha vinto!";
    public static final String RIVELAZIONE_EQUILIBRIO   = "\nEcco svelato l'Equilibrio del Mondo:";
    public static final String INPUT_GIOCATORE = "Inserisci il nome del giocatore ";
    public static final String ERRORE_INPUT_GIOCATORE = "Il giocatore deve avere un nome";
    public static final String CONFERMA_NUOVO_SCONTRO = "\nVuoi iniziare un nuovo scontro?";
    public static final String BENVENUTO = BelleStringhe.incornicia("Benvenuto nel gioco dei TamaGolem");
    public static final String RICHIESTA_NUMERO_ELEMENTI = "Inserisci il numero di elementi con cui vuoi giocare (min: 3, max : 10): ";
    public static final String ERRORE_NUMERO_ELEMENTI = "Numero di elementi non valido";
    public static final String MESSAGGIO_GOLEM_SCONFITTO = "È stato sconfitto il golem di ";
    public static final String GOLEM_RIMASTI = "Golem rimasti ";
    public static final String ERRORE_PIETRE = "Le pietre selezionate dal secondo giocatore sono uguali a quelle del primo\nSeleziona nuove pietre ";
    public static final String MESSAGGIO_TENTATA_USCITA = "\nNon è possibile uscire in questo punto del programma";
    public static final String MESSAGGIO_PIETRE_FINITE = "Non ci sono piu' pietre di questo elemento";
    public static final String MESSAGGIO_DANNO_NULLO = "Le pietre dei due golem sono dello stesso elemento, nessuno subisce danno";
    public static final String MESSAGGIO_SET_UGUALI = "\n" + BelleStringhe.incornicia("I set di pietre dei due tamagolem sono uguali, ciò comporterebbe uno scontro infinito\nÈ necessario riselezionare l'ultimo set");
}