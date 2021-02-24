package it.unicam.cs.ids2021.c3.notifiche;

public interface NotificaAggiornamentoInt<T> {

    T getOggetto();

    int getID();

    String getType();

}