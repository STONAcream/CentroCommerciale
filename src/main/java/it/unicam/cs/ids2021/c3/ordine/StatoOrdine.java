package it.unicam.cs.ids2021.c3.ordine;

public enum StatoOrdine {

    ATTESA, //24 ore per cancellare ordine

    PREPARAZIONE, // appena pagato

    SPEDITO,

    CONSEGNATO, // completo

    ANNULLATO, // completo

    RECLAMO

}