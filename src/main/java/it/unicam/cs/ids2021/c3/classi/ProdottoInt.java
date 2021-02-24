package it.unicam.cs.ids2021.c3.classi;

public interface ProdottoInt {

    void addQuantita(int qta);

    void decreaseQuantita(int qta);

    void updateQuantita(int qta);

    void updatePrezzo(double prezzo);

    String getNome();

    double getPrezzo();

    int getQuantita();

    String getDescrizione();

    int getID();

    CommercianteInt getCommerciante();

}