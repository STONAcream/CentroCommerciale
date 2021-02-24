package it.unicam.cs.ids2021.c3.sistema;

import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;

public class Pagamento {

    private ContoCorrenteInt creditore;
    private ContoCorrenteInt debitore;
    private double importo;

    public Pagamento(ContoCorrenteInt creditore, ContoCorrenteInt debitore, double importo) {
        this.creditore = creditore;
        this.debitore = debitore;
        this.importo = importo;
    }

    public void paga() {
        this.debitore.decreaseSaldo(importo);
        this.creditore.increaseSaldo(importo);
    }

}