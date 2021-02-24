package it.unicam.cs.ids2021.c3.database;

import it.unicam.cs.ids2021.c3.classi.*;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamento;
import it.unicam.cs.ids2021.c3.notifiche.NotificaAggiornamentoInt;
import it.unicam.cs.ids2021.c3.ordine.Ordine;
import it.unicam.cs.ids2021.c3.ordine.OrdineInt;
import it.unicam.cs.ids2021.c3.ordine.StatoOrdine;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrente;
import it.unicam.cs.ids2021.c3.pagamenti.ContoCorrenteInt;
import it.unicam.cs.ids2021.c3.reso.Reso;
import it.unicam.cs.ids2021.c3.reso.ResoInt;

import java.sql.*;

public class DBConnection {

    private DB database;
    private static final String url = "jdbc:mysql://localhost:3306/admin";
    private static final String user = "admin";
    private static final String pwd = "pwd";
    private Connection conn = null;

    public DBConnection(DB database) {
        this.database = database;
    }

    public void connetti() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            if (conn != null) {
                System.out.println("CONNESSIONE ESEGUITA!");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void leggi() {
        leggiContoCorrente();
        leggiCliente();
        leggiCommerciante();
        leggiCorriere();
        leggiProdotto();
        leggiOrdine();
        leggiReso();
        leggiNotifica();
    }

    private void leggiContoCorrente() {
        String query = "SELECT iban, saldo FROM `contocorrente`";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ContoCorrenteInt cc = new ContoCorrente(rs.getString(1), rs.getDouble(2));
                database.getContoCorrente().put(cc.getIban(), cc);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiCommerciante() {
        String query = "SELECT piva, titolare, indirizzo, email, password, tel, contocorrente FROM commerciante";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CommercianteInt com = new Commerciante(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), database.getContoCorrente().get(rs.getString(7)));
                database.getCommercianti().put(com.getPIva(), com);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiCorriere() {
        String query = "SELECT piva, titolare, sede, email, password, telefono, puntodiconsegna FROM corriere";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CorriereInt cor = new Corriere(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                database.getCorrieri().put(cor.getPIva(), cor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiCliente() {
        String query = "SELECT nome, cognome, email, password, indirizzo, telefono, contocorrente FROM cliente";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteInt cliente = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), database.getContoCorrente().get(rs.getString(7)));
                database.getClienti().put(cliente.getEmail(), cliente);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiProdotto() {
        String query = "SELECT id, nome, descrizione, prezzo, qta, commerciante FROM prodotto";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdottoInt prod = new Prodotto(rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getInt(1), database.getCommercianti().get(rs.getString(6)));
                database.getProdotti().put(prod.getID(), prod);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiOrdine() {
        String query = "SELECT id, cliente, prod, data, importo, corriere, stato, indirizzo, indirizzofinale FROM ordine";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrdineInt ordine = new Ordine(rs.getInt(1), database.getClienti().get(rs.getString(2)), database.getProdotti().get(rs.getInt(3)), rs.getDate(4), rs.getDouble(5), database.getCorrieri().get(rs.getString(6)), StatoOrdine.valueOf(rs.getString(7)), rs.getString(8), rs.getString(9));
                database.getOrdini().put(ordine.getID(), ordine);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiReso() {
        String query = "SELECT id, ordine FROM reso";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResoInt reso = new Reso(rs.getInt(1), database.getOrdini().get(rs.getInt(2)));
                database.getResi().put(reso.getID(), reso);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void leggiNotifica() {
        String query = "SELECT id, oggetto, tipo FROM notifica";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (rs.getString(3).equals("P")) {
                    NotificaAggiornamentoInt notifica = new NotificaAggiornamento(rs.getInt(1), database.getProdotti().get(rs.getInt(2)), "P");
                    database.getNotifiche().put(notifica.getID(), notifica);
                } else {
                    NotificaAggiornamentoInt notifica = new NotificaAggiornamento(rs.getInt(1), database.getOrdini().get(rs.getInt(2)), "O");
                    database.getNotifiche().put(notifica.getID(), notifica);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void aggiorna() {
        String del1 = "DELETE FROM contocorrente";
        String del2 = "DELETE FROM commerciante";
        String del3 = "DELETE FROM cliente";
        String del4 = "DELETE FROM corriere";
        String del5 = "DELETE FROM prodotto";
        String del6 = "DELETE FROM ordine";
        String del7 = "DELETE FROM reso";
        String del8 = "DELETE FROM notifica";
        try {
            PreparedStatement ps1 = conn.prepareStatement(del1);
            ps1.executeUpdate();
            PreparedStatement ps2 = conn.prepareStatement(del2);
            ps2.executeUpdate();
            PreparedStatement ps3 = conn.prepareStatement(del3);
            ps3.executeUpdate();
            PreparedStatement ps4 = conn.prepareStatement(del4);
            ps4.executeUpdate();
            PreparedStatement ps5 = conn.prepareStatement(del5);
            ps5.executeUpdate();
            PreparedStatement ps6 = conn.prepareStatement(del6);
            ps6.executeUpdate();
            PreparedStatement ps7 = conn.prepareStatement(del7);
            ps7.executeUpdate();
            PreparedStatement ps8 = conn.prepareStatement(del8);
            ps8.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        aggiornaContocorrente();
        aggiornaCommerciante();
        aggiornaCliente();
        aggiornaCorriere();
        aggiornaProdotto();
        aggiornaOrdine();
        aggiornaReso();
        aggiornaNotifica();
    }

    private void aggiornaContocorrente() {
        String query = "INSERT INTO contocorrente(iban, saldo) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (ContoCorrenteInt c : database.getContoCorrente().values()) {
                ps.setString(1, c.getIban());
                ps.setDouble(2, c.getSaldo());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaCommerciante() {
        String query = "INSERT INTO commerciante(piva, titolare, indirizzo, email, password, tel, contocorrete) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (CommercianteInt c : database.getCommercianti().values()) {
                ps.setString(1, c.getPIva());
                ps.setString(2, c.getTitolare());
                ps.setString(3, c.getIndirizzo());
                ps.setString(4, c.getEmail());
                ps.setString(5, c.getPassword());
                ps.setString(6, c.getTelefono());
                ps.setString(7, c.getContoCorrente().getIban());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaCorriere() {
        String query = "INSERT INTO corriere(piva, titolare, sede, email, password, telefono, puntodiconsegna) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (CorriereInt c : database.getCorrieri().values()) {
                ps.setString(1, c.getPIva());
                ps.setString(2, c.getTitolare());
                ps.setString(3, c.getSede());
                ps.setString(4, c.getEmail());
                ps.setString(5, c.getPassword());
                ps.setString(6, c.getTelefono());
                ps.setString(7, c.getIndirizzo());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaCliente() {
        String query = "INSERT INTO cliente(nome, cognome, email, password, indirizzo, telefono, contocorrete) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (ClienteInt c : database.getClienti().values()) {
                ps.setString(1, c.getNome());
                ps.setString(2, c.getCognome());
                ps.setString(3, c.getEmail());
                ps.setString(4, c.getPassword());
                ps.setString(5, c.getIndirizzo());
                ps.setString(6, c.getTelefono());
                ps.setString(7, c.getContoCorrente().getIban());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaProdotto() {
        String query = "INSERT INTO prodotto(id, nome, descrizione, prezzo, qta, commerciante) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (ProdottoInt c : database.getProdotti().values()) {
                ps.setInt(1, c.getID());
                ps.setString(2, c.getNome());
                ps.setString(3, c.getDescrizione());
                ps.setDouble(4, c.getPrezzo());
                ps.setInt(5, c.getQuantita());
                ps.setString(6, c.getCommerciante().getPIva());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaOrdine() {
        String query = "INSERT INTO ordine(id, cliente, prod, data, importo, corriere, stato, indirizzo, indirizzofinale) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (OrdineInt c : database.getOrdini().values()) {
                ps.setInt(1, c.getID());
                ps.setString(2, c.getCliente().getEmail());
                ps.setInt(3, c.getProdotto().getID());
                ps.setDate(4, (Date) c.getData());
                ps.setDouble(5, c.getImporto());
                ps.setString(6, c.getCorriere().getPIva());
                ps.setString(7, c.getStato().toString());
                ps.setString(8, c.getIndirizzo());
                ps.setString(8, c.getIndirizzoFinale());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaReso() {
        String query = "INSERT INTO reso(id, ordine) VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (ResoInt c : database.getResi().values()) {
                ps.setInt(1, c.getID());
                ps.setInt(2, c.getOrdine().getID());
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void aggiornaNotifica() {
        String query = "INSERT INTO notifica(id, oggetto, tipo) VALUES(?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            for (NotificaAggiornamentoInt c : database.getNotifiche().values()) {
                ps.setInt(1, c.getID());
                if (c.getOggetto().getClass() == ProdottoInt.class) {
                    ProdottoInt p = (ProdottoInt) c.getOggetto();
                    ps.setInt(2, p.getID());
                    ps.setString(3, c.getType());
                } else {
                    OrdineInt o = (OrdineInt) c.getOggetto();
                    ps.setInt(2, o.getID());
                    ps.setString(3, c.getType());
                }
                ps.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}