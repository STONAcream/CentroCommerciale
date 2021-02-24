package it.unicam.cs.ids2021.c3.gestioneAttori;

import it.unicam.cs.ids2021.c3.database.DB;
import it.unicam.cs.ids2021.c3.ordine.Ordine;
import it.unicam.cs.ids2021.c3.ordine.OrdineInt;
import it.unicam.cs.ids2021.c3.ordine.StatoOrdine;
import it.unicam.cs.ids2021.c3.classi.ClienteInt;
import it.unicam.cs.ids2021.c3.classi.CorriereInt;
import it.unicam.cs.ids2021.c3.classi.ProdottoInt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GestoreCliente {

    private ClienteInt cliente;
    private DB database;

    public GestoreCliente(DB database) {
        this.database = database;
    }

    public void preparazioneCarrello() {
        String desc;
        Scanner input = new Scanner(System.in);
        desc = input.next();
        List<ProdottoInt> prod = new ArrayList<>();
        for (ProdottoInt p : database.getProdotti().values()) {
            if (p.getDescrizione().contains(desc)) {
                prod.add(p);
            }
        }
        for (ProdottoInt p : prod) {
            String risposta;
            risposta = input.next();
            if (risposta != "S") {
                prod.remove(p);
            }
        }
        for (ProdottoInt p : prod) {
            int qta;
            qta = input.nextInt();
            if (database.getProdotti().get(p.getID()).getQuantita() >= qta) {
                aggiuntaCarrello(qta, p.getID());
            } else {
                System.out.println("oggetti aggiunti alla lista dei preferiti");
                aggiuntaPreferiti(qta, p.getID());
            }
        }
        input.close();
    }

    public void creaOrdine() {
        Scanner risposta = new Scanner(System.in);
        for (ProdottoInt p : cliente.getCarrello()) {
            System.out.println(p.toString());
            String r = risposta.next();
            if (!r.equals("S")) {
                cliente.getCarrello().remove(p);
            }
        }
        for (ProdottoInt p : cliente.getCarrello()) {
            int ID = database.getOrdini().size() + 1;
            for (CorriereInt c : database.getCorrieri().values()) {
                System.out.println(c.toString());
            }
            System.out.println("Inserisci la PIva del corriere:");
            String corriere = risposta.next();
            OrdineInt ordine = new Ordine(ID, this.cliente, p, new Date(), database.getCorrieri().get(corriere));
            database.getOrdini().put(ID, ordine);
            database.getProdotti().get(p.getID()).decreaseQuantita(p.getQuantita());
        }
        risposta.close();
    }

    public void annullaOrdine() {
        Date d = new Date();
        int c = 0;
        for (OrdineInt o : database.getOrdini().values()) {
            if (o.getCliente() == this.cliente && d.getTime() - o.getData().getTime() < 86400000) {
                c++;
                System.out.println(o.toString());
            }
        }
        if (c != 0) {
            Scanner input = new Scanner(System.in);
            System.out.println("Inserisci l'ID dell'ordine da annullare: ");
            int ID = input.nextInt();
            database.getOrdini().get(ID).aggiornaStato(StatoOrdine.ANNULLATO);
            System.out.println("Ordine annullato.");
            input.close();
        } else {
            System.out.println("Non ci sono ordini da annullare.");
        }
    }

    private void aggiuntaPreferiti(int qta, int ID) {
        for (int i = 0; i < qta; i++) {
            cliente.getPreferiti().add(database.getProdotti().get(ID));
        }
    }

    private void aggiuntaCarrello(int qta, int ID) {
        for (int i = 0; i < qta; i++) {
            cliente.getCarrello().add(database.getProdotti().get(ID));
        }
    }

    public double getTotaleCarrello() {
        double totale = 0;
        for (ProdottoInt p : cliente.getCarrello()) {
            totale += p.getPrezzo();
        }
        return totale;
    }

    public void setCliente(ClienteInt c) {
        this.cliente = c;
    }

}