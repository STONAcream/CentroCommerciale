package it.unicam.cs.ids2021.c3.ordine;

import it.unicam.cs.ids2021.c3.classi.ClienteInt;
import it.unicam.cs.ids2021.c3.classi.CorriereInt;
import it.unicam.cs.ids2021.c3.classi.ProdottoInt;

import java.util.Date;

public interface OrdineInt {

    int getID();

    ClienteInt getCliente();

    ProdottoInt getProdotto();

    Date getData();

    double getImporto();

    CorriereInt getCorriere();

    StatoOrdine getStato();

    void aggiornaStato(StatoOrdine stato);

    String getIndirizzo();

    String getIndirizzoFinale();

    void setIndirizzoFinale(String address);

}