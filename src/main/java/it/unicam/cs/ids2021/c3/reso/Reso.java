package it.unicam.cs.ids2021.c3.reso;

import it.unicam.cs.ids2021.c3.ordine.OrdineInt;

public class Reso implements ResoInt{

    private int ID; // chiave della tabella
    private OrdineInt ordine;

    public Reso(int ID, OrdineInt ordine) {
        this.ID = ID;
        this.ordine = ordine;
    }

    public int getID() {
        return ID;
    }

    public OrdineInt getOrdine() {
        return ordine;
    }

}