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
public class Etudiants {
    private int NUMETUDIANT;
    private int CODEDIP;
    private String NOMET;
    private String PRENOMET;
    private String EMAIL;
    private String CV;

    public int getNUMETUDIANT() {
        return NUMETUDIANT;
    }

    public void setNUMETUDIANT(int NUMETUDIANT) {
        this.NUMETUDIANT = NUMETUDIANT;
    }

    public int getCODEDIP() {
        return CODEDIP;
    }

    public void setCODEDIP(int CODEDIP) {
        this.CODEDIP = CODEDIP;
    }

    public String getNOMET() {
        return NOMET;
    }

    public void setNOMET(String NOMET) {
        this.NOMET = NOMET;
    }

    public String getPRENOMET() {
        return PRENOMET;
    }

    public void setPRENOMET(String PRENOMET) {
        this.PRENOMET = PRENOMET;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getCV() {
        return CV;
    }

    public void setCV(String CV) {
        this.CV = CV;
    }
    
    public static ArrayList<Etudiants> getAll() throws SQLException {
        ArrayList<Etudiants> listEtu = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT NUMETUDIANT, NOMET, PRENOMET FROM etudiants;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                Etudiants etu = new Etudiants();
                etu.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                etu.setNOMET(result.getString("NOMET"));
                etu.setPRENOMET(result.getString("PRENOMET"));                  
                listEtu.add(etu);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listEtu;
    }
    
    public Etudiants getOne(int numEtu) throws SQLException {
        Etudiants etu = null;
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM etudiants WHERE NUMETUDIANT = '"+ numEtu +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                etu = new Etudiants();
                etu.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                etu.setNOMET(result.getString("NOMET"));
                etu.setPRENOMET(result.getString("PRENOMET"));
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return etu;
    }
}
