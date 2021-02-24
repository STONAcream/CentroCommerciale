package it.unicam.cs.ids2021.c3.classi;

import java.util.Objects;

public class Prodotto implements ProdottoInt {

    private int ID; // chiave della tabella
    private String nome;
    private String descrizione;
    private double prezzo;
    private int quantita;
    private CommercianteInt commerciante;

    public Prodotto(String nome, String descrizione, double prezzo, int quantita, int ID, CommercianteInt commerciante) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.quantita = quantita;
        this.ID = ID;
        this.commerciante = commerciante;
    }

    public void addQuantita(int qta) {
        this.quantita += qta;
    }

    public void decreaseQuantita(int qta) {
        if (qta <= this.quantita) {
            this.quantita -= qta;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void updateQuantita(int qta) {
        assert qta > 0;
        this.quantita = qta;
    }

    public void updatePrezzo(double prezzo) {
        assert prezzo > 0;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public int getQuantita() {
        return quantita;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getID() {
        return ID;
    }

    public CommercianteInt getCommerciante() {
        return commerciante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return ID == prodotto.ID && commerciante.equals(prodotto.commerciante);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, commerciante);
    }

}