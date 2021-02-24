package it.unicam.cs.ids2021.c3.notifiche;

public class NotificaAggiornamento<T> implements NotificaAggiornamentoInt{

    private int ID;
    private T oggetto;
    private String type;

    public NotificaAggiornamento(int ID, T oggetto, String type) {
        this.ID = ID;
        this.oggetto = oggetto;
        this.type = type;
    }

    @Override
    public T getOggetto() {
        return oggetto;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String getType() {
        return type;
    }

}
