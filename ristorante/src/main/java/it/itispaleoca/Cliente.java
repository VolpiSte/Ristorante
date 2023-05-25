package it.itispaleoca;

public class Cliente implements Comparable<Cliente>{
    private String nome;
    private int prenotazioniEffettuate, copertiPrenotati;

    Cliente(String nome){
        this.nome = nome;
        prenotazioniEffettuate = 0;
        copertiPrenotati = 0;
    }

    @Override
    public String toString(){
        return "Nome cliente: "+ nome
                +"\nPrenotazioni effettuate: "+String.valueOf(prenotazioniEffettuate)
                +"\nCoperti prenotati totali: "+String.valueOf(copertiPrenotati);
    }

    //getters
    public String getNome() {
        return nome;
    }
    public int getPrenotazioniEffettuate() {
        return prenotazioniEffettuate;
    }
    public int getCopertiPrenotati() {
        return copertiPrenotati;
    }

    //setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setCopertiPrenotati(int copertiPrenotati) {
        this.copertiPrenotati += copertiPrenotati;
    }
    public void setPrenotazioniEffettuate() {
        this.prenotazioniEffettuate++;
    }



    @Override
    public int compareTo(Cliente o) {
        return Integer.compare(this.prenotazioniEffettuate, o.prenotazioniEffettuate);
    }
}