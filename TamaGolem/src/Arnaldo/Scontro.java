package Arnaldo;

import java.util.ArrayList;

public class Scontro {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private static ArrayList<Pietra> scortaDiPietre = new ArrayList<>();

    /**
     * Quantità di pietre nella scorta comune
     */
    public static final int S = (int)(Equilibrio.getN() * Math.ceil((2*Giocatore.G*Pietra.P)/Equilibrio.getN()));

    public Scontro(Giocatore giocatore1, Giocatore giocatore2) {
        this.giocatore1 = giocatore1;
        this.giocatore2 = giocatore2;
        riempiScortaDiPietre();
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

    /**
     * Riempie la scorta di pietre con S/N pietre di ciascun elemento
     */
    public void riempiScortaDiPietre() {
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
    public void scontroGolem(TamaGolem golem1, TamaGolem golem2) {
        int i = golem1.getSetdiPietre().element().getElemento().ordinal();
        int j = golem2.getSetdiPietre().element().getElemento().ordinal();

        while (golem1.inVita() && golem2.inVita()) {
            if (confrontoPietre(golem1.getSetdiPietre().element(), golem2.getSetdiPietre().element())) {
                int danno = Equilibrio.getTabellaEquilibrio()[i][j];
                golem2.subisciDanno(danno);
                InterazioneUtenti.mostraDanno(danno, giocatore2);
            }

            else {
                int danno = Equilibrio.getTabellaEquilibrio()[j][i];
                golem1.subisciDanno(danno);
                InterazioneUtenti.mostraDanno(danno, giocatore1);
            }

            golem1.getSetdiPietre().add(golem1.getSetdiPietre().poll());
            golem2.getSetdiPietre().add(golem2.getSetdiPietre().poll());
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

        if (possessoreDelTamagolem.getTamaGolemEliminati() == Giocatore.G) {
            determinaVincitore(possessoreDelTamagolem);
        } else {
            possessoreDelTamagolem.generaTamaGolem();
            possessoreDelTamagolem.prelevaPietre();
        }
    }

    /**
     * Termina lo scontro dichiarando il vincitore 
     * @param perdente
     */
    public void determinaVincitore(Giocatore perdente){
        if (perdente.equals(giocatore1)){
            giocatore1.sconfitta();
        }
        else {
            giocatore2.sconfitta();
        }
    }



}