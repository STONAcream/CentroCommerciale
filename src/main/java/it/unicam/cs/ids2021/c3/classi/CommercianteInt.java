package it.unicam.cs.ids2021.c3.classi;

import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrente;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;

public interface CommercianteInt {

    String getPIva();

    String getTitolare();

    String getIndirizzo();

    String getEmail();

    String getPassword();

    String getTelefono();

    ContoCorrenteInt getContoCorrente();

}