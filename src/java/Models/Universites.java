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
 * @author jtrescartes
 */
public class Universites {
    private int CODEU;
    private String NOMU;
    private String ADRESSEPOSTU;
    private String ADRESSEWEBU;
    private String ADRESSEELECTU;

    public int getCODEU() {
        return CODEU;
    }

    public void setCODEU(int CODEU) {
        this.CODEU = CODEU;
    }

    public String getNOMU() {
        return NOMU;
    }

    public void setNOMU(String NOMU) {
        this.NOMU = NOMU;
    }

    public String getADRESSEPOSTU() {
        return ADRESSEPOSTU;
    }

    public void setADRESSEPOSTU(String ADRESSEPOSTU) {
        this.ADRESSEPOSTU = ADRESSEPOSTU;
    }

    public String getADRESSEWEBU() {
        return ADRESSEWEBU;
    }

    public void setADRESSEWEBU(String ADRESSEWEBU) {
        this.ADRESSEWEBU = ADRESSEWEBU;
    }

    public String getADRESSEELECTU() {
        return ADRESSEELECTU;
    }

    public void setADRESSEELECTU(String ADRESSEELECTU) {
        this.ADRESSEELECTU = ADRESSEELECTU;
    }
    
    public static ArrayList<Universites> getAll() throws SQLException {
        ArrayList<Universites> listUni = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT CODEU, NOMU FROM universites;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                Universites uni = new Universites();
                uni.setCODEU(result.getInt("CODEU"));
                uni.setNOMU(result.getString("NOMU"));                
                listUni.add(uni);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listUni;
    }
    
    public Universites getOne(int codeUni) throws SQLException {
        Universites uni = null;
        Connection conn = Utils.connect();
        String sql = "SELECT CODEU, NOMU FROM universites WHERE CODEU = '"+ codeUni +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                uni = new Universites();
                uni.setCODEU(result.getInt("CODEU"));
                uni.setNOMU(result.getString("NOMU")); 
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return uni;
    }
}
