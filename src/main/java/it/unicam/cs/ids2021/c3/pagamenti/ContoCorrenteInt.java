package it.unicam.cs.ids2021.c3.pagamenti;

public interface ContoCorrenteInt {

    String getIban();

    double getSaldo();

    void decreaseSaldo(double importo);

    void increaseSaldo(double importo);

}