package it.unicam.cs.ids2021.c3.sistema;

import it.unicam.cs.ids2021.c3.database.DB;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.ordine.OrdineInt;
import it.unicam.cs.ids2021.c3.classi.ClienteInt;
import it.unicam.cs.ids2021.c3.classi.ProdottoInt;

public class InviaNotifiche {

    private NotificaAggiornamentoInt notifica;
    private DB database;

    public InviaNotifiche(NotificaAggiornamentoInt notifica, DB database) {
        this.notifica = notifica;
        this.database = database;
    }

    public void invia() {
        if (notifica.getOggetto().getClass() == ProdottoInt.class) {
            for (ClienteInt c : database.getClienti().values()) {
                if (c.getPreferiti().contains(notifica.getOggetto())) {
                    c.getNotifiche().add(notifica);
                }
            }
        } else {
            OrdineInt o = (OrdineInt) notifica.getOggetto();
            ClienteInt c = o.getCliente();
            c.getNotifiche().add(notifica);
        }
    }

}
