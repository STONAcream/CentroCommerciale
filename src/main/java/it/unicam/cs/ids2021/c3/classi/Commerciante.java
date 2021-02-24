package it.unicam.cs.ids2021.c3.classi;

import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrente;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;

import java.util.Objects;

public class Commerciante implements CommercianteInt {

    private String PIva; // chiave della tabella
    private String titolare;
    private String indirizzo;
    private String email;
    private String password;
    private String telefono;
    private ContoCorrenteInt contoCorrente;

    public Commerciante(String PIva, String titolare, String indirizzo, String email, String password, String telefono, ContoCorrenteInt contoCorrente) {
        this.PIva = PIva;
        this.titolare = titolare;
        this.indirizzo = indirizzo;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.contoCorrente = contoCorrente;
    }

    public String getPIva() {
        return PIva;
    }

    public String getTitolare() {
        return titolare;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getTelefono() {
        return telefono;
    }

    public ContoCorrenteInt getContoCorrente() {
        return contoCorrente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commerciante that = (Commerciante) o;
        return Objects.equals(PIva, that.PIva);
    }

    @Override
    public int hashCode() {
        return Objects.hash(PIva);
    }
}