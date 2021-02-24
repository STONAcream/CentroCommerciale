package it.unicam.cs.ids2021.c3.ordine;

import it.unicam.cs.ids2021.c3.classi.ClienteInt;
import it.unicam.cs.ids2021.c3.classi.CorriereInt;
import it.unicam.cs.ids2021.c3.classi.ProdottoInt;

import java.util.Date;

public class Ordine implements OrdineInt{

    private int ID; // chiave della tabella
    private ClienteInt cliente;
    private ProdottoInt prodotto;
    private Date data;
    private double importo;
    private CorriereInt corriere;
    private StatoOrdine stato;
    private String indirizzo;
    private String indirizzoFinale;

    public Ordine(int ID, ClienteInt cliente, ProdottoInt prodotto, Date data, CorriereInt corriere) {
        this.ID = ID;
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.data = data;
        this.corriere = corriere;
        this.stato = StatoOrdine.ATTESA;
        this.indirizzo = cliente.getIndirizzo();
        this.indirizzoFinale = indirizzo;
        this.importo = prodotto.getPrezzo();
    }

    public Ordine(int ID, ClienteInt cliente, ProdottoInt prodotto, Date data, double importo, CorriereInt corriere,StatoOrdine stato,  String indirizzo, String indirizzoFinale) {
        this.ID = ID;
        this.cliente = cliente;
        this.prodotto = prodotto;
        this.data = data;
        this.corriere = corriere;
        this.stato = stato;
        this.indirizzo = indirizzo;
        this.indirizzoFinale = indirizzoFinale;
        this.importo = importo;
    }

    public int getID() {
        return ID;
    }

    public ClienteInt getCliente() {
        return cliente;
    }

    public ProdottoInt getProdotto() {
        return prodotto;
    }

    public Date getData() {
        return data;
    }

    public double getImporto() {
        return importo;
    }

    public CorriereInt getCorriere() {
        return corriere;
    }

    public StatoOrdine getStato() {
        return stato;
    }

    public void aggiornaStato(StatoOrdine stato) {
        this.stato = stato;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzoFinale(String address) {
        this.indirizzoFinale = address;
    }

    public String getIndirizzoFinale() {
        return indirizzoFinale;
    }

}