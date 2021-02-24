package it.unicam.cs.ids2021.c3.classi;

import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;

import java.util.*;

public class Cliente implements ClienteInt {

    private String nome;
    private String cognome;
    private String email; // chiave della tabella
    private String password;
    private String indirizzo;
    private String telefono;
    private ContoCorrenteInt contoCorrente;
    private List<ProdottoInt> carrello;
    private List<ProdottoInt> preferiti;
    private List<NotificaAggiornamentoInt> notifiche;

    public Cliente(String nome, String cognome, String email, String password, String indirizzo, String telefono,ContoCorrenteInt contoCorrente) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.contoCorrente = contoCorrente;
        this.carrello = new ArrayList<>();
        this.preferiti = new ArrayList<>();
        this.notifiche = new ArrayList<>();
    }


    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getTelefono() {
        return telefono;
    }

    @Override
    public List<ProdottoInt> getCarrello() {
        return carrello;
    }

    @Override
    public List<ProdottoInt> getPreferiti() {
        return preferiti;
    }

    public ContoCorrenteInt getContoCorrente() {
        return contoCorrente;
    }

    @Override
    public List<NotificaAggiornamentoInt> getNotifiche() {
        return notifiche;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return email.equals(cliente.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}