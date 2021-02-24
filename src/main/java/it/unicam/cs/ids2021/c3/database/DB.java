package it.unicam.cs.ids2021.c3.database;

import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.ordine.OrdineInt;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;
import it.unicam.cs.ids2021.c3.reso.ResoInt;
import it.unicam.cs.ids2021.c3.classi.ClienteInt;
import it.unicam.cs.ids2021.c3.classi.CommercianteInt;
import it.unicam.cs.ids2021.c3.classi.CorriereInt;
import it.unicam.cs.ids2021.c3.classi.ProdottoInt;

import java.util.*;

public class DB {

    private HashMap<String, CommercianteInt> commercianti;
    private HashMap<String, CorriereInt> corrieri;
    private HashMap<String, ClienteInt> clienti;
    private HashMap<Integer, ProdottoInt> prodotti;
    private HashMap<Integer, OrdineInt> ordini;
    private HashMap<Integer, NotificaAggiornamentoInt> notifiche;
    private HashMap<Integer, ResoInt> resi;
    private HashMap<String, ContoCorrenteInt> contoCorrente;


    public DB() {
        this.clienti = new HashMap<>();
        this.commercianti = new HashMap<>();
        this.corrieri = new HashMap<>();
        this.notifiche = new HashMap<>();
        this.prodotti = new HashMap<>();
        this.ordini = new HashMap<>();
        this.resi = new HashMap<>();
        this.contoCorrente = new HashMap<>();
    }

    public HashMap<String, CommercianteInt> getCommercianti() {
        return commercianti;
    }

    public HashMap<String, CorriereInt> getCorrieri() {
        return corrieri;
    }

    public HashMap<Integer, NotificaAggiornamentoInt> getNotifiche() {
        return notifiche;
    }

    public HashMap<Integer, OrdineInt> getOrdini() {
        return ordini;
    }

    public HashMap<Integer, ProdottoInt> getProdotti() {
        return prodotti;
    }

    public HashMap<Integer, ResoInt> getResi() {
        return resi;
    }

    public HashMap<String, ClienteInt> getClienti() {
        return clienti;
    }

    public HashMap<String, ContoCorrenteInt> getContoCorrente() {
        return contoCorrente;
    }

}