package Arnaldo;

import java.util.ArrayList;

public class Scontro {
    
    private Giocatore Giocatore1 = new Giocatore();
    private Giocatore Giocatore2 = new Giocatore();
    private Equilibrio equilibrio;
    private ArrayList <Pietre> scortaDiPietre = new ArrayList<>();


    public static final int S = (int)Math.ceil(2*Giocatore.G*Pietre.P/Equilibrio.N) * Equilibrio.N;
}
