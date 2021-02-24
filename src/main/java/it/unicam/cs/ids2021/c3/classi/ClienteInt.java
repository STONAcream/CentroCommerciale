package it.unicam.cs.ids2021.c3.classi;

import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;

import java.util.List;

public interface ClienteInt {

    String getNome();

    String getCognome();

    String getEmail();

    String getPassword();

    String getIndirizzo();

    String getTelefono();

    List<ProdottoInt> getCarrello();

    List<ProdottoInt> getPreferiti();

    ContoCorrenteInt getContoCorrente();

    List<NotificaAggiornamentoInt> getNotifiche();
}
