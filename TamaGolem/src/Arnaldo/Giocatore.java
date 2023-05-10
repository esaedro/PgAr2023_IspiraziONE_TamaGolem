package Arnaldo;

import java.util.ArrayList;

/**
 * Classe istanziabile per rappresentare i giocatori della partita
 */
public class Giocatore {

    /**
    *  Numero di golem per ogni giocatore
    */
    private static int G;
    private TamaGolem tamaGolemAttuale;
    private int tamaGolemEliminati;
    private String nome;
    private boolean haPerso;
    

    public Giocatore(String nome) {
        this.nome = nome;
        this.tamaGolemAttuale = new TamaGolem();
        this.tamaGolemEliminati = 0;
        this.haPerso = false;
    }

    public static int getG() {
        return G;
    }

    public static void setG(int g) {
        G = g;
    }

    public String getNome() {
        return nome;
    }

    public TamaGolem getTamaGolemAttuale() {
        return tamaGolemAttuale;
    }

    public int getTamaGolemEliminati() {
        return tamaGolemEliminati;
    }

    public boolean getHaPerso() {
        return haPerso;
    }

    /**
     * Genera un nuovo tamagolem, chiamata quando muore il golem precedente
     */
    public void generaTamaGolem() {
        tamaGolemAttuale = new TamaGolem();
    }

    /**
     * Incrementa il numero di golem eliminati quando un golem del giocatore muore
     */
    public void aumentaTamagolemEliminati() {
        this.tamaGolemEliminati += 1;
    }

    /**
     * Stabilisce se un giocatore ha perso, quando non ha golem rimasti
     */
    public void sconfitta() {
        haPerso = true;
    }

    /**
     * Metodo che aggiunge alla scorta del giocatore le pietre selezionate dalla pila comune
     */
    public void prelevaPietre(Scontro scontro){
        Pietra pietraSelezionata;
        ArrayList<Pietra> scortaModificata = new ArrayList<>(Scontro.getScortaDiPietre());
        int pietrePerTamagolem = Pietra.getP(), pietreUguali = 0;
        tamaGolemAttuale.svuotaSetDiPietre();

        System.out.println("\n" + this.nome + ", scegli un totale di " + pietrePerTamagolem + " pietre");

        for(int i = 0; i < pietrePerTamagolem; i++){
            pietraSelezionata = InterazioneUtenti.selezionaPietra(this, scortaModificata);
            scortaModificata.remove(pietraSelezionata);
            tamaGolemAttuale.aggiungiPietra(pietraSelezionata);
        }

        try{
            Pietra[] pietre1 = scontro.getGiocatore1().tamaGolemAttuale.getSetDiPietre().toArray(new Pietra[pietrePerTamagolem]);
            Pietra[] pietre2 = scontro.getGiocatore2().tamaGolemAttuale.getSetDiPietre().toArray(new Pietra[pietrePerTamagolem]);

            for (pietreUguali = 0; pietreUguali < pietrePerTamagolem && pietre1[pietreUguali].getElemento().equals(pietre2[pietreUguali].getElemento()); pietreUguali++);

            if (pietreUguali == pietrePerTamagolem) {
                System.out.println(Frasi.MESSAGGIO_SET_UGUALI);
                this.tamaGolemAttuale.svuotaSetDiPietre();
                prelevaPietre(scontro);
            } else {
                Scontro.getScortaDiPietre().retainAll(scortaModificata);
            }
        } catch (Exception NullPointerException){
            Scontro.getScortaDiPietre().retainAll(scortaModificata);
        }
    }
}