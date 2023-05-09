package Arnaldo;

import java.util.ArrayList;

public class Scontro {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private static ArrayList<Pietra> scortaDiPietre = new ArrayList<>();
    private static int turno;

    /**
     * Quantità di pietre nella scorta comune
     */
    private static int S;

    public Scontro(Giocatore giocatore1, Giocatore giocatore2) {
        this.giocatore1 = giocatore1;
        this.giocatore2 = giocatore2;
        riempiScortaDiPietre();
        resetTurno();
    }

    public static int getS() {
        return S;
    }

    public static int getTurno() {
        return turno;
    }

    public static ArrayList<Pietra> getScortaDiPietre() {
        return scortaDiPietre;
    }

    public Giocatore getGiocatore1() {
        return giocatore1;
    }

    public Giocatore getGiocatore2() {
        return giocatore2;
    }

    public static void setS(int s) {
        S = s;
    }

    public static void incrementaTurno() {
        turno++;
    }
    
    public static void resetTurno() {
        turno = 1;
    }

    /**
     * Riempie la scorta di pietre con S/N pietre di ciascun elemento
     */
    public void riempiScortaDiPietre() {
        scortaDiPietre.clear();
        for (int i = 0; i < Equilibrio.getN(); i++) {
            for (int j = 0; j < S/Equilibrio.getN(); j++) { 
                scortaDiPietre.add(new Pietra(Elemento.values()[i]));    //Pietra che ha come elemento quello di posizione j
            }
        }
    }

    /**
     * Gestisce lo scontro tra golem, infliggendo danno a chi perde il confronto delle pietre
     * @param golem1
     * @param golem2
     */ 
    public void scontroGolem(Giocatore giocatore1, Giocatore giocatore2) {
        int i, j;
        TamaGolem golem1 = giocatore1.getTamaGolemAttuale();
        TamaGolem golem2 = giocatore2.getTamaGolemAttuale();

        while (golem1.inVita() && golem2.inVita()) {
            i = golem1.getSetDiPietre().element().getElemento().ordinal();
            j = golem2.getSetDiPietre().element().getElemento().ordinal();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            if (confrontoPietre(golem1.getSetDiPietre().element(), golem2.getSetDiPietre().element())) {
                int danno = Equilibrio.getTabellaEquilibrio()[i][j];
                golem2.subisciDanno(danno);
                InterazioneUtenti.mostraAzione(danno, giocatore2, giocatore1);
            }

            else {
                int danno = Equilibrio.getTabellaEquilibrio()[j][i];
                golem1.subisciDanno(danno);
                InterazioneUtenti.mostraAzione(danno, giocatore1, giocatore2);
            }

            golem1.giraSet();
            golem2.giraSet();

            incrementaTurno();
        }

        if (!golem1.inVita()) {
            morteTamagolem(giocatore1);
        }
        else {
            morteTamagolem(giocatore2);
        }

    }
   
    /**
     * Confronta gli elementi delle due pietre scagliate e stabilisce qual è quello forte
     * @param pietra1
     * @param pietra2
     * @return vincitore dello scontro (true: primo golem, false: secondo golem)
     */
    public boolean confrontoPietre(Pietra pietra1, Pietra pietra2) {    //confronto tra pietre, chi ha quella debole subisce danno
        int i = pietra1.getElemento().ordinal();
        int j = pietra2.getElemento().ordinal();                        //se primo elemento sul secondo nella matrice e' > 0 allora vince

        return Equilibrio.getTabellaEquilibrio()[i][j] > 0 ;            //true se primo elemento vince sul secondo
    }

    /**
     * Gestisce la morte di un tamagolem generandone uno nuovo per il giocatore oppure terminando lo scontro se si sono esauriti i tamagolem
     * @param possessoreDelTamagolem
     */
    public void morteTamagolem(Giocatore possessoreDelTamagolem) {
        possessoreDelTamagolem.aumentaTamagolemEliminati();
        InterazioneUtenti.morteTamagolem(possessoreDelTamagolem);

        if (possessoreDelTamagolem.getTamaGolemEliminati() == Giocatore.getG()) {
            determinaVincitore(possessoreDelTamagolem);
        } else {
            possessoreDelTamagolem.generaTamaGolem();
            possessoreDelTamagolem.prelevaPietre(this);
        }
    }

    /**
     * Termina lo scontro dichiarando il vincitore 
     * @param perdente
     */
    public void determinaVincitore(Giocatore perdente) {
        if (perdente.equals(giocatore1)) {
            giocatore1.sconfitta();
        } else {
            giocatore2.sconfitta();
        }
    }

}