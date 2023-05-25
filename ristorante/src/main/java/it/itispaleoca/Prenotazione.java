package it.itispaleoca;

import java.time.LocalDate;

public class Prenotazione{
    private Cliente cliente;
    private LocalDate quando,perQuando;
    private int quantiCoperti;
    private int id;

    Prenotazione(Cliente cliente, LocalDate quando, LocalDate perQuando, int quantiCoperti, int id){
        this.cliente = cliente;
        this.quando = quando;
        this.perQuando = perQuando;
        this.quantiCoperti = quantiCoperti;
        this.id = id;
    }
    @Override
    public String toString() {
        return cliente.toString()
                +"\nPrenotazione n."+String.valueOf(id)
                +"\nin data: "+quando.toString()
                +"\nper data: "+perQuando.toString()
                +"\nNumero di coperti: "+String.valueOf(quantiCoperti);
    }
    //getters
    public int getId() {
        return id;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public LocalDate getQuando() {
        return quando;
    }
    public LocalDate getPerQuando() {
        return perQuando;
    }
    public int getQuantiCoperti() {
        return quantiCoperti;
    }
    //setters
    public void setCliente(Cliente cliente) {
        if(cliente == null){
            return;
        }
        this.cliente = cliente;
    }
    public void setPerQuando(LocalDate perQuando) {
        if(perQuando == null){
            return;
        }
        this.perQuando = perQuando;
    }
    public void setQuantiCoperti(int quantiCoperti) {
        if(quantiCoperti <= 0){
            return;
        }
        this.quantiCoperti = quantiCoperti;
    }
    public void setQuando(LocalDate quando) {
        if(quando == null){
            return;
        }
        this.quando = quando;
    }
}