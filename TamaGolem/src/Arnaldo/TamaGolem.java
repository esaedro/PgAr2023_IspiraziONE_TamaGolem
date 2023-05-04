package Arnaldo;

import java.util.ArrayDeque;
import java.util.Deque;

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

    public void setSetdiPietre(Deque<Pietra> setdiPietre) {
        this.setdiPietre = setdiPietre;
    }
 
    public void subisciDanno(int danno) {
        vita -= danno;
    }

    public boolean inVita() {
        return vita > 0;
    }
}
