package Arnaldo;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Classe istanziabile che rappresenta i TamaGolem
 */
public class TamaGolem {

    private int vita;
    private Deque<Pietra> setdiPietre = new ArrayDeque<>(Pietra.P);
    
    public TamaGolem() {
        this.vita = Equilibrio.getPotenzaMassima();
    }

    public void aggiungiPietra(Pietra p){
        setdiPietre.add(p); 
    }

    public int getVita() {
        return vita;
    }

    public Deque<Pietra> getSetdiPietre() {
        return setdiPietre;
    }
 
    public void subisciDanno(int danno) {
        vita -= danno;
    }

    public boolean inVita() {
        return vita > 0;
    }

    public String toString () {
        StringBuffer descrizione = new StringBuffer();
        descrizione.append(String.format("Vita: %d", vita));
        descrizione.append("\nPietra corrente di tipo: " + setdiPietre.element().toString());
        return descrizione.toString(); 
    }
}
