/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author julien
 */
public class Contrats {
    private int IDCONTRAT;
    private int CODEDIP;
    private int REFDEMMOB;
    private int IDP;
    private String DUREE;
    private String ETATCONTRAT;
    private String ORDRE;

    public int getIDCONTRAT() {
        return IDCONTRAT;
    }

    public void setIDCONTRAT(int IDCONTRAT) {
        this.IDCONTRAT = IDCONTRAT;
    }

    public int getCODEDIP() {
        return CODEDIP;
    }

    public void setCODEDIP(int CODEDIP) {
        this.CODEDIP = CODEDIP;
    }

    public int getREFDEMMOB() {
        return REFDEMMOB;
    }

    public void setREFDEMMOB(int REFDEMMOB) {
        this.REFDEMMOB = REFDEMMOB;
    }

    public int getIDP() {
        return IDP;
    }

    public void setIDP(int IDP) {
        this.IDP = IDP;
    }

    public String getDUREE() {
        return DUREE;
    }

    public void setDUREE(String DUREE) {
        this.DUREE = DUREE;
    }

    public String getETATCONTRAT() {
        return ETATCONTRAT;
    }

    public void setETATCONTRAT(String ETATCONTRAT) {
        this.ETATCONTRAT = ETATCONTRAT;
    }

    public String getORDRE() {
        return ORDRE;
    }

    public void setORDRE(String ORDRE) {
        this.ORDRE = ORDRE;
    }
    
    public static ArrayList<Contrats> getAll() throws SQLException {
        ArrayList<Contrats> listCon = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM contrats;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                Contrats con = new Contrats();
                con.setIDCONTRAT(result.getInt("IDCONTRAT"));
                con.setCODEDIP(result.getInt("CODEDIP"));
                con.setREFDEMMOB(result.getInt("REFDEMMOB"));
                con.setIDP(result.getInt("IDP"));
                con.setDUREE(result.getString("DUREE"));
                con.setETATCONTRAT(result.getString("ETATCONTRAT"));
                con.setORDRE(result.getString("ORDRE"));
                listCon.add(con);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listCon;
    }
    
    public static ArrayList<Contrats> getAllByProgram(int idP) throws SQLException {
        ArrayList<Contrats> listCon = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM contrats INNER JOIN programmes ON contrats.IDP = programmes.IDP WHERE programmes.IDP = '" + idP + "';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                Contrats con = new Contrats();
                con.setIDCONTRAT(result.getInt("IDCONTRAT"));
                con.setCODEDIP(result.getInt("CODEDIP"));
                con.setREFDEMMOB(result.getInt("REFDEMMOB"));
                con.setIDP(result.getInt("IDP"));
                con.setDUREE(result.getString("DUREE"));
                con.setETATCONTRAT(result.getString("ETATCONTRAT"));
                con.setORDRE(result.getString("ORDRE"));
                listCon.add(con);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listCon;
    }
}
