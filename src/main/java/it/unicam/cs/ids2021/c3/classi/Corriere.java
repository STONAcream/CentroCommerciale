package it.unicam.cs.ids2021.c3.classi;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Corriere implements CorriereInt {

    private String PIva; // chiave della tabella
    private String titolare;
    private String sede;
    private String email;
    private String password;
    private String telefono;
    private String indirizzo;

    public Corriere(String PIva, String titolare, String sede, String email, String password, String telefono, String indirizzo) {
        this.PIva = PIva;
        this.titolare = titolare;
        this.sede = sede;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
    }

    public String getPIva() {
        return PIva;
    }

    public String getTitolare() {
        return titolare;
    }

    public String getSede() {
        return sede;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Corriere corriere = (Corriere) o;
        return PIva.equals(corriere.PIva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PIva);
    }

}