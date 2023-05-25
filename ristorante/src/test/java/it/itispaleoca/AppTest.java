package it.itispaleoca;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AppTest {

    @Test
    public void testAddCliente() {
        Ristorante ristorante = new Ristorante(10);
        ristorante.addCliente("Mario");
        Cliente cliente = ristorante.ricercaCliente("Mario");
        assertEquals("Mario", cliente.getNome());
    }

    @Test
    public void testDeleteCliente() {
        Ristorante ristorante = new Ristorante(10);
        ristorante.addCliente("Mario");
        ristorante.deleteCliente("Mario");
        Cliente cliente = ristorante.ricercaCliente("Mario");
        assertNull(cliente);
    }

    @Test
    public void testAddPrenotazione() {
        Ristorante ristorante = new Ristorante(10);
        Cliente cliente = new Cliente("Mario");
        ristorante.addCliente(cliente);
        LocalDate oggi = LocalDate.now();
        LocalDate domani = oggi.plusDays(1);
        ristorante.addPrenotazione(cliente, oggi, domani, 5);
        LinkedList<Prenotazione> prenotazioni = ristorante.ricercaPrenotazioniBasedOn(cliente);
        assertEquals(1, prenotazioni.size());
    }

    @Test
    public void testModificaPrenotazione() {
        Ristorante ristorante = new Ristorante(10);
        Cliente cliente = new Cliente("Mario");
        ristorante.addCliente(cliente);
        LocalDate oggi = LocalDate.now();
        LocalDate domani = oggi.plusDays(1);
        ristorante.addPrenotazione(cliente, oggi, domani, 5);
        Prenotazione prenotazione = ristorante.ricercaPrenotazioniBasedOn(cliente).get(0);
        LocalDate nuovoPerQuando = domani.plusDays(1);
        ristorante.modificaPrenotazione(prenotazione, cliente, oggi, nuovoPerQuando, 8);
        assertEquals(nuovoPerQuando, prenotazione.getPerQuando());
        assertEquals(8, prenotazione.getQuantiCoperti());
    }

    @Test
    public void testRemovePrenotazione() {
        Ristorante ristorante = new Ristorante(10);
        Cliente cliente = new Cliente("Mario");
        ristorante.addCliente(cliente);
        LocalDate oggi = LocalDate.now();
        LocalDate domani = oggi.plusDays(1);
        ristorante.addPrenotazione(cliente, oggi, domani, 5);
        Prenotazione prenotazione = ristorante.ricercaPrenotazioniBasedOn(cliente).get(0);
        ristorante.removePrenotazione(prenotazione);
        LinkedList<Prenotazione> prenotazioni = ristorante.ricercaPrenotazioniBasedOn(cliente);
        assertEquals(0, prenotazioni.size());
    }

    @Test
    public void testRicercaPrenotazioniBasedOn() {
        Ristorante ristorante = new Ristorante(10);
        Cliente cliente1 = new Cliente("Mario");
        Cliente cliente2 = new Cliente("Luigi");
        ristorante.addCliente(cliente1);
        ristorante.addCliente(cliente2);
        LocalDate oggi = LocalDate.now();
        LocalDate domani = oggi.plusDays(1);
        ristorante.addPrenotazione(cliente1, oggi, domani, 5);
        ristorante.addPrenotazione(cliente2, oggi, domani, 3);
        LinkedList<Prenotazione> prenotazioniMario = ristorante.ricercaPrenotazioniBasedOn(cliente1);
        LinkedList<Prenotazione> prenotazioniLuigi = ristorante.ricercaPrenotazioniBasedOn(cliente2);
        assertEquals(1, prenotazioniMario.size());
        assertEquals(1, prenotazioniLuigi.size());
    }
}
