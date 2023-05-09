package Arnaldo;

public class MainTamaGolem {
    public static void main(String[] args) {
        System.out.println(Frasi.BENVENUTO);
        Scontro scontro;
        do{
            InterazioneUtenti.inizializzaVariabiliPartita(InterazioneUtenti.selezionaNumeroDiElementi());
            Equilibrio.generaEquilibrio();

            scontro = new Scontro(InterazioneUtenti.inserimentoGiocatore(1), InterazioneUtenti.inserimentoGiocatore(2));
            InterazioneUtenti.stampaCostanti();
            scontro.getGiocatore1().prelevaPietre(scontro);
            scontro.getGiocatore2().prelevaPietre(scontro);
            System.out.println(scontro.getGiocatore1().getTamaGolemAttuale().getSetDiPietre());
            System.out.println(scontro.getGiocatore2().getTamaGolemAttuale().getSetDiPietre());
            // InterazioneUtenti.sceltaPietre(scontro.getGiocatore1(), scontro.getGiocatore2());
        
            while (!(scontro.getGiocatore1().getHaPerso() || scontro.getGiocatore2().getHaPerso())){
                scontro.scontroGolem(scontro.getGiocatore1().getTamaGolemAttuale(), scontro.getGiocatore2().getTamaGolemAttuale());
            }

            InterazioneUtenti.terminaScontro(scontro.getGiocatore1().getHaPerso() ? scontro.getGiocatore2() : scontro.getGiocatore1());
        } while (InterazioneUtenti.continuaPartita());
    } 
}

/**
 * TODO:
 * - Quando si inserisce zero nelle pietre non esce dal ciclo ma toglie quella di elemento zero
 * - Correggere quando i set di pietre sono uguali
 */