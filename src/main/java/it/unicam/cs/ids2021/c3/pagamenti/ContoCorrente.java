package it.unicam.cs.ids2021.c3.pagamenti;

public class ContoCorrente implements ContoCorrenteInt {

    private String iban;
    private double saldo;

    public ContoCorrente(String iban, double saldo) {
        this.iban = iban;
        this.saldo= saldo;
    }

    public String getIban() {
        return iban;
    }

    public double getSaldo() {
        return saldo;
    }

    public void decreaseSaldo(double importo) {
        assert saldo >= importo;
        this.saldo -= importo;
    }

    public void increaseSaldo(double importo) {
        this.saldo += importo;
    }

}