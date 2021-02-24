/**
 *
 * AUTORI: Compagnucci Lorenzo, matricola 105354
 *         Finocchi Alessandro, matricola 105194
 *
 */

package it.unicam.cs.ids2021.c3;

import it.unicam.cs.ids2021.c3.database.*;
import it.unicam.cs.ids2021.c3.gestioneAttori.*;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.ordine.OrdineInt;
import it.unicam.cs.ids2021.c3.ordine.StatoOrdine;
import it.unicam.cs.ids2021.c3.classi.ClienteInt;
import it.unicam.cs.ids2021.c3.classi.CommercianteInt;
import it.unicam.cs.ids2021.c3.classi.CorriereInt;
import it.unicam.cs.ids2021.c3.sistema.*;

import java.util.Date;
import java.util.Scanner;

public class Main {

    private static DB database = new DB();

    public static void main(String[] args) {

        DBConnection db = new DBConnection(database);
        db.connetti();
        db.leggi();

        System.out.println("Scegli come utilizzare il programma");
        System.out.println("1 - Cliente");
        System.out.println("2 - Commerciante");
        System.out.println("3 - Corriere");
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int r = 0;
        switch (a) {
            case 1:
                GestoreCliente cliente = new GestoreCliente(database);
                System.out.println("Inserisci l'email: ");
                String email = input.next();
                System.out.println("Inserisci la password: ");
                String pwd = input.next();
                for (ClienteInt c : database.getClienti().values()) {
                    if (c.getEmail().equals(email) && c.getPassword().equals(pwd)) {
                        cliente.setCliente(c);
                    }
                }
                while (r == 0) {
                    System.out.println("SCEGLI L'AZIONE DA COMPIERE: ");
                    System.out.println("1 - Aggiungi dei prodotti al carello");
                    System.out.println("2 - Crea un nuovo ordine");
                    System.out.println("3 - Annulla un ordine");
                    System.out.println("4 - Visualizza il totale del carrello");
                    int scelta = input.nextInt();
                    switch (scelta) {
                        case 1:
                            cliente.preparazioneCarrello();
                            break;
                        case 2:
                            cliente.creaOrdine();
                            break;
                        case 3:
                            cliente.annullaOrdine();
                            break;
                        case 4:
                            System.out.println("Totale carrello: " + cliente.getTotaleCarrello());
                            break;
                    }
                    System.out.println("Inserisci '0' (zero) per continuare: ");
                    r = input.nextInt();
                }
                break;
            case 2:
                GestoreCommerciante commerciante = new GestoreCommerciante(database);
                System.out.println("Inserisci l'email: ");
                email = input.next();
                System.out.println("Inserisci la password: ");
                pwd = input.next();
                for (CommercianteInt c : database.getCommercianti().values()) {
                    if (c.getEmail().equals(email) && c.getPassword().equals(pwd)) {
                        commerciante.setCommerciante(c);
                    }
                }
                while (r == 0) {
                    System.out.println("SCEGLI L'AZIONE DA COMPIERE: ");
                    System.out.println("1 - Aggiorna il listino");
                    System.out.println("2 - Effettua un rimborso");
                    int scelta = input.nextInt();
                    switch (scelta) {
                        case 1:
                            commerciante.aggiornamentoListino();
                            break;
                        case 2:
                            commerciante.rimborso();
                            break;
                    }
                    System.out.println("Inserisci '0' (zero) per continuare: ");
                    r = input.nextInt();
                }
                break;
            case 3:
                GestoreCorriere corriere = new GestoreCorriere(database);
                System.out.println("Inserisci l'email: ");
                email = input.next();
                System.out.println("Inserisci la password: ");
                pwd = input.next();
                for (CorriereInt c : database.getCorrieri().values()) {
                    if (c.getEmail().equals(email) && c.getPassword().equals(pwd)) {
                        corriere.setCorriere(c);
                    }
                }
                while (r == 0) {
                    System.out.println("SCEGLI L'AZIONE DA COMPIERE: ");
                    System.out.println("1 - Aggiorna lo stato di un ordine");
                    System.out.println("2 - Visualizza gli ordini attivi");
                    int scelta = input.nextInt();
                    switch (scelta) {
                        case 1:
                            corriere.aggiornaStatoSpedizione();
                            break;
                        case 2:
                            corriere.visualizzaOrdiniAttivi();
                            break;
                    }
                    System.out.println("Inserisci '0' (zero) per continuare: ");
                    r = input.nextInt();
                }
                break;
        }
        input.close();
        aggiornaOrdini();
        aggiornaNotifiche();
        db.aggiorna();
    }

    private static void aggiornaOrdini() {
        Date d = new Date();
        for (OrdineInt o : database.getOrdini().values()) {
            if (o.getStato() == StatoOrdine.ATTESA && (d.getTime() - o.getData().getTime() > 86400000)) {
                o.aggiornaStato(StatoOrdine.PREPARAZIONE);
                Pagamento p = new Pagamento(o.getProdotto().getCommerciante().getContoCorrente(), o.getCliente().getContoCorrente(), o.getImporto());
                p.paga();
            }
        }
    }

    private static void aggiornaNotifiche() {
        for (NotificaAggiornamentoInt n : database.getNotifiche().values()) {
            InviaNotifiche inviaNotifiche = new InviaNotifiche(n, database);
            inviaNotifiche.invia();
        }
    }

}