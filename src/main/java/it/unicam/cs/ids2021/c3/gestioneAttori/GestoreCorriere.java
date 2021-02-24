package it.unicam.cs.ids2021.c3.gestioneAttori;

import it.unicam.cs.ids2021.c3.database.DB;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamento;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.ordine.OrdineInt;
import it.unicam.cs.ids2021.c3.ordine.StatoOrdine;
import it.unicam.cs.ids2021.c3.classi.CorriereInt;

import java.util.Scanner;

public class GestoreCorriere {

    private CorriereInt corriere;
    private DB database;

    public GestoreCorriere(DB database) {
        this.database = database;
    }

    public void aggiornaStatoSpedizione() {
        for (OrdineInt o : database.getOrdini().values()) {
            if (o.getCorriere().equals(this.corriere)) {
                System.out.println(o.toString());
            }
        }
        Scanner input = new Scanner(System.in);
        System.out.println("INSERISCI IL CODICE DELL'ORDINE DA AGGIORNARE");
        int codice = input.nextInt();
        System.out.println("INSERISCI IL NUOVO STATO: ");
        System.out.println("SPEDITO - S");
        System.out.println("CONSEGNATO - C");
        System.out.println("RECLAMO - R");
        char stato = input.next().charAt(0);
        switch (stato) {
            case 'S':
                database.getOrdini().get(codice).aggiornaStato(StatoOrdine.SPEDITO);
                break;
            case 'C':
                database.getOrdini().get(codice).aggiornaStato(StatoOrdine.CONSEGNATO);
                break;
            case 'R':
                database.getOrdini().get(codice).aggiornaStato(StatoOrdine.RECLAMO);
                break;
        }
        input.close();
        NotificaAggiornamentoInt<OrdineInt> n = new NotificaAggiornamento<OrdineInt>(database.getNotifiche().size()+1,database.getOrdini().get(codice), "O");
        database.getNotifiche().put(database.getNotifiche().size()+1, n);
    }

    public void setCorriere(CorriereInt corriere) {
        this.corriere = corriere;
    }

    public void visualizzaOrdiniAttivi() {
        for (OrdineInt o : database.getOrdini().values()) {
            if (o.getCorriere().equals(this.corriere) && (o.getStato()== StatoOrdine.RECLAMO || o.getStato()== StatoOrdine.SPEDITO)) {
                System.out.println(o.toString());
            }
        }
    }
}
