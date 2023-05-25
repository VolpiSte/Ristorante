package it.itispaleoca;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.time.LocalDate;

public class Ristorante {
    private HashMap<Integer, Prenotazione> prenotazioni;
    private HashMap<String, Cliente>clienti;
    private Cliente bestCliente;
    private int coperti,prenotazioniTotali,maxP,maxC;
    private LocalDate giornoMigliore;
    Ristorante(int coperti){
        prenotazioni = new HashMap<>();
        clienti = new HashMap<>();
        this.coperti = coperti;
        prenotazioniTotali = 0;
    }
    @Override
    public String toString() {
        String message = "Ristorante Bello prenotazioni:\n";
        for (Prenotazione p: prenotazioni.values()) {
            message+=p.toString()+"\n";
        }
        return message;
    }

    public Ristorante addCliente(String nome){
        Cliente c = new Cliente(nome);
        clienti.put(nome, c);
        return this;
    }
    public Ristorante addCliente(Cliente c){
        clienti.put(c.getNome(), c);
        return this;
    }



    public void modifyCliente(Cliente c, String nome){
        c.setNome(nome);
    }
    public void modifyCliente(String nome, String nuovoNome){
        clienti.get(nome).setNome(nuovoNome);
    }



    public void deleteCliente(Cliente c){
        clienti.remove(c.getNome(), c);
    }
    public void deleteCliente(String nome){
        Cliente c = clienti.get(nome);
        clienti.remove(nome, c);
    }



    public Ristorante addPrenotazione(Prenotazione p){
        prenotazioni.put(prenotazioniTotali, p);
        prenotazioniTotali++;
        return this;
    }
    public Ristorante addPrenotazione(Cliente c, LocalDate quando, LocalDate perQuando, int coperti){
        Prenotazione p = new Prenotazione(c, quando, perQuando, coperti, prenotazioniTotali);
        prenotazioni.put(prenotazioniTotali, p);
        prenotazioniTotali++;
        c.setCopertiPrenotati(coperti);
        c.setPrenotazioniEffettuate();
        return this;
    }



    public void modificaPrenotazione(Prenotazione p, Cliente c, LocalDate quando, LocalDate perQuando, int coperti){
        p.setCliente(c);
        p.setQuando(perQuando);
        p.setPerQuando(perQuando);
        p.setQuantiCoperti(coperti);
    }
    public void modificaPrenotazione(int idPrenotazione, Cliente c, LocalDate quando, LocalDate perQuando, int coperti){
        Prenotazione p = prenotazioni.get(idPrenotazione);
        p.setCliente(c);
        p.setQuando(perQuando);
        p.setPerQuando(perQuando);
        p.setQuantiCoperti(coperti);
    }



    public void removePrenotazione(Prenotazione p){
        prenotazioni.remove(p.getId(), p);
        prenotazioniTotali--;
    }
    public void removePrenotazione(int idPrenotazione){
        Prenotazione p = prenotazioni.get(idPrenotazione);
        prenotazioni.remove(idPrenotazione, p);
        prenotazioniTotali--;
    }



    public Cliente ricercaCliente(String nome){
        return clienti.get(nome);
    }


    public LinkedList<Prenotazione> ricercaPrenotazioniBasedOn(Cliente c){
        LinkedList<Prenotazione> ps = new LinkedList<>();
        prenotazioni.values().stream()
                    .filter(x -> x.getCliente().equals(c))
                    .forEach(ps::add);
        return ps;
    }



    public LinkedList<Prenotazione> ricercaPrenotazioniQuando(LocalDate d){
        LinkedList<Prenotazione> ps = new LinkedList<>();
        prenotazioni.values().stream()
                    .filter(x -> x.getQuando().equals(d))
                    .forEach(ps::add);
        return ps;
    }
    public LinkedList<Prenotazione> ricercaPrenotazioniPerQuando(LocalDate d){
        LinkedList<Prenotazione> ps = new LinkedList<>();
        prenotazioni.values().stream()
                    .filter(x -> x.getPerQuando().equals(d))
                    .forEach(ps::add);
        return ps;
    }



    public int ricercaCopertiBasedOn(LocalDate d){
        LinkedList<Integer> inters = new LinkedList<>();
        prenotazioni.values().stream()
                    .filter(x -> x.getQuando().equals(d))
                    .forEach(x -> inters.add(x.getQuantiCoperti()));
        int copertiTot = inters.stream().mapToInt(Integer::intValue).sum();
        return copertiTot;
    }

    

    public int ricercaCopertiInRange(LocalDate from, LocalDate to){
        LinkedList<Integer> inters = new LinkedList<>();
        prenotazioni.values().stream()
                    .filter(x -> x.getQuando().isAfter(from) && x.getQuando().isBefore(to))
                    .forEach(x -> inters.add(x.getQuantiCoperti()));
        int copertiTot = inters.stream().mapToInt(Integer::intValue).sum();
        return copertiTot;
    }



    public LocalDate ricercaMaxCoperti(){
        maxP = prenotazioni.get(0).getQuantiCoperti();
        prenotazioni.values().stream()
                             .forEach(x -> {
                                if (x.getQuantiCoperti()>maxP) {
                                    maxP = x.getQuantiCoperti();
                                    giornoMigliore = x.getQuando();        
                                }
                             }
                             );
                             
        return giornoMigliore;
    }

    

    public Cliente ricercaMaxPrenotati(){
        maxC = 0;
        clienti.values().stream()
                        .forEach(x ->{
                            if(x.getCopertiPrenotati()>maxC){
                                maxC = x.getCopertiPrenotati();
                                bestCliente = x;
                            }
                        });
        return bestCliente;
    }



    public LinkedList<Cliente> clientiList(){
        LinkedList<Cliente> c = new LinkedList<>(clienti.values());
        Collections.sort(c);
        return c;
    }
}
