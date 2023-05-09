package Arnaldo;

public class MainTamaGolem {
    public static void main(String[] args) {
        System.out.println(Frasi.BENVENUTO);
        Scontro scontro;
        do{
            InterazioneUtenti.inizializzaVariabiliPartita(InterazioneUtenti.selezionaNumeroDiElementi());
            Equilibrio.generaEquilibrio();

            scontro = new Scontro(InterazioneUtenti.inserimentoGiocatore(1), InterazioneUtenti.inserimentoGiocatore(2));
            //InterazioneUtenti.stampaCostanti();
            scontro.getGiocatore1().prelevaPietre(scontro);
            scontro.getGiocatore2().prelevaPietre(scontro);
        
            while (!(scontro.getGiocatore1().getHaPerso() || scontro.getGiocatore2().getHaPerso())){
                scontro.scontroGolem(scontro.getGiocatore1(), scontro.getGiocatore2());
            }

            InterazioneUtenti.terminaScontro(scontro.getGiocatore1().getHaPerso() ? scontro.getGiocatore2() : scontro.getGiocatore1());
        } while (InterazioneUtenti.continuaPartita());
    } 
}