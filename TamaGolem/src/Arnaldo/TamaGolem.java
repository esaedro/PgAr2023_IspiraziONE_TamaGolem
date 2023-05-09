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

    public boolean inVita() {
        return vita > 0;
    }

    public void giraSet() {
        setDiPietre.add(setDiPietre.poll());
    }

    public String toString () {
        StringBuffer descrizione = new StringBuffer();
        descrizione.append(String.format("Vita: %d", vita));
        descrizione.append("\nPietra corrente di tipo: " + setDiPietre.element().toString());
        return descrizione.toString(); 
    }
}
