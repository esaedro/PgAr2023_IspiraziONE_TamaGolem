package Arnaldo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classe istanziabile che rappresenta i TamaGolem
 */
public class TamaGolem {

    private int vita;
    private Deque<Pietra> setDiPietre = new ArrayDeque<>(Pietra.getP());
    
    public TamaGolem() {
        this.vita = Equilibrio.getPotenzaMassima();
    }

    public int getVita() {
        return vita;
    }

    public Deque<Pietra> getSetDiPietre() {
        return setDiPietre;
    }

    public void svuotaSetDiPietre() {
        setDiPietre.clear();
    }
 
    public void aggiungiPietra(Pietra p){
        setDiPietre.add(p); 
    }

    public void subisciDanno(int danno) {
        vita -= danno;
    }

    /**
     * Controlla se il golem è ancora vivo
     * @return true se la vita è ancora positiva
     */
    public boolean inVita() {
        return vita > 0;
    }

    /**
     * Fa girare il set di pietre spostando l'utlima pietra all'inizio della coda
     */
    public void giraSet() {
        setDiPietre.add(setDiPietre.poll());
    }
}