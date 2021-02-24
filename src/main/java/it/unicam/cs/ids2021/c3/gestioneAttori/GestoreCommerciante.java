package it.unicam.cs.ids2021.c3.gestioneAttori;

import it.unicam.cs.ids2021.c3.database.DB;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamento;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.sistema.Pagamento;
import it.unicam.cs.ids2021.c3.reso.ResoInt;
import it.unicam.cs.ids2021.c3.classi.CommercianteInt;
import it.unicam.cs.ids2021.c3.classi.Prodotto;
import it.unicam.cs.ids2021.c3.classi.ProdottoInt;

import java.util.Scanner;

public class GestoreCommerciante {

    private CommercianteInt commerciante;
    private DB database;

    public GestoreCommerciante(DB database) {
        this.database = database;
    }

    public void aggiornamentoListino() {
        for (ProdottoInt p : database.getProdotti().values()) {
            if (p.getCommerciante().equals(this.commerciante)) {
                System.out.println(p.toString());
            }
        }
        char risposta;
        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Inserisci l'ID del prodotto: ");
            int ID = input.nextInt();
            if (database.getProdotti().containsKey(ID)) {
                modificaProdotto(ID);
            } else {
                creazioneProdotto();
            }
            System.out.println("Vuoi continuare ad aggiornare? (S/N)");
            risposta = input.next().charAt(0);
        } while (risposta == 'S');
        input.close();
    }

    private void modificaProdotto(int ID) {
        System.out.println("Vuoi eliminare o modificare il prodotto? (E/M)");
        Scanner input = new Scanner(System.in);
        char scelta = input.next().charAt(0);
        if (scelta == 'E') {
            database.getProdotti().remove(ID);
        } else {
            System.out.println("Inserisci la nuova quantità: ");
            int qta = input.nextInt();
            database.getProdotti().get(ID).updateQuantita(qta);
            System.out.println("Inserisci il nuovo prezzo: ");
            double prezzo = input.nextDouble();
            database.getProdotti().get(ID).updatePrezzo(prezzo);
            NotificaAggiornamentoInt n = new NotificaAggiornamento(database.getNotifiche().size(), database.getProdotti().get(ID), "P");
            database.getNotifiche().put(n.getID(), n);
        }
    }

    private void creazioneProdotto() {
        System.out.println("Vuoi creare un nuovo prodotto? (S/N)");
        Scanner input = new Scanner(System.in);
        char risposta = input.next().charAt(0);
        if (risposta == 'S') {
            System.out.println("Inserisci il nome: ");
            String nome = input.next();
            System.out.println("Inserisci la descrizione: ");
            String descrizione = input.next();
            System.out.println("Inserisci il prezzo: ");
            double prezzo = input.nextDouble();
            System.out.println("Inserisci la quantità: ");
            int quantita = input.nextInt();
            int ID = database.getProdotti().size(); // ???
            ProdottoInt p = new Prodotto(nome, descrizione, prezzo, quantita, ID, this.commerciante);
            database.getProdotti().put(ID, p);
        }
    }

    public void rimborso() {
        for (ResoInt r : database.getResi().values()) {
            if (r.getOrdine().getProdotto().getCommerciante().equals(commerciante)) {
                System.out.println(r.toString());
            }
        }
        System.out.println("Scegli il reso da rimborsare: ");
        Scanner input = new Scanner(System.in);
        int ID = input.nextInt();
        Pagamento p = new Pagamento(database.getResi().get(ID).getOrdine().getCliente().getContoCorrente(), commerciante.getContoCorrente(), database.getResi().get(ID).getOrdine().getImporto());
        p.paga();
        database.getProdotti().get(database.getResi().get(ID).getOrdine().getProdotto().getID()).addQuantita(1);
    }

    public void setCommerciante(CommercianteInt commerciante) {
        this.commerciante = commerciante;
    }
}