package Arnaldo;
public class Giocatore {

    /**
    *  Numero di golem per ogni giocatore
    */
    public static final int G = (int)Math.ceil((Equilibrio.N-1)*(Equilibrio.N-2)/2*Pietra.P);
    private TamaGolem tamaGolemAttuale;
    private int tamaGolemEliminati;
    private String nome;
    

    public Giocatore(String nome) {
        this.nome = nome;
        this.tamaGolemAttuale = new TamaGolem();
        this.tamaGolemEliminati = 0;
    }

    public String getNome() {
        return nome;
    }

    public TamaGolem getTamaGolemAttuale() {
        return tamaGolemAttuale;
    }

    public void generaTamaGolem() {
        tamaGolemAttuale = new TamaGolem();
    }

    public int getTamaGolemEliminati() {
        return tamaGolemEliminati;
    }

    public void aumentaTamagolemEliminati() {
        this.tamaGolemEliminati += 1;
    }

    /**
     * Metodo che aggiunge alla scorta del giocatore le pietre selezionate dalla pila comune
     */
    public void prelevaPietre(){
        Pietra pietraSelezionata;
        for(int i = 0; i < Pietra.P; i++){
            pietraSelezionata = InterazioneUtenti.selezionaPietra();
            Scontro.getScortaDiPietre().remove(pietraSelezionata);
            tamaGolemAttuale.aggiungiPietra(pietraSelezionata);
        }
    }
}
