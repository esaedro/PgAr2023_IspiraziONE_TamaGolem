package Arnaldo;
public class Giocatore {

    /**
    *  Numero di golem per ogni giocatore
    */
    public static final int G = (int)Math.ceil(((Equilibrio.getN()-1)*(Equilibrio.getN()-2))/(2*Pietra.P));
    private TamaGolem tamaGolemAttuale;
    private int tamaGolemEliminati;
    private String nome;
    private boolean haPerso;
    

    public Giocatore(String nome) {
        this.nome = nome;
        this.tamaGolemAttuale = new TamaGolem();
        this.tamaGolemEliminati = 0;
        this.haPerso = false;
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

    public boolean getHaPerso() {
        return haPerso;
    }

    public void aumentaTamagolemEliminati() {
        this.tamaGolemEliminati += 1;
    }

    public void sconfitta() {
        haPerso = true;
    }

    /**
     * Metodo che aggiunge alla scorta del giocatore le pietre selezionate dalla pila comune
     */
    public void prelevaPietre(){
        Pietra pietraSelezionata;
        for(int i = 0; i < Pietra.P; i++){
            pietraSelezionata = InterazioneUtenti.selezionaPietra(this);
            Scontro.getScortaDiPietre().remove(pietraSelezionata);
            tamaGolemAttuale.aggiungiPietra(pietraSelezionata);
        }
    }
}
