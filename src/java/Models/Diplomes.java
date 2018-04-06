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
public class Diplomes {
    private int CODEDIP;
    private int CODEU;
    private String INTITULEDIP;
    private String ADRESSEWEBD;
    private int NIVEAU;

    public int getCODEDIP() {
        return CODEDIP;
    }

    public void setCODEDIP(int CODEDIP) {
        this.CODEDIP = CODEDIP;
    }

    public int getCODEU() {
        return CODEU;
    }

    public void setCODEU(int CODEU) {
        this.CODEU = CODEU;
    }

    public String getINTITULEDIP() {
        return INTITULEDIP;
    }

    public void setINTITULEDIP(String INTITULEDIP) {
        this.INTITULEDIP = INTITULEDIP;
    }

    public String getADRESSEWEBD() {
        return ADRESSEWEBD;
    }

    public void setADRESSEWEBD(String ADRESSEWEBD) {
        this.ADRESSEWEBD = ADRESSEWEBD;
    }

    public int getNIVEAU() {
        return NIVEAU;
    }

    public void setNIVEAU(int NIVEAU) {
        this.NIVEAU = NIVEAU;
    }
    
    public static ArrayList<Diplomes> getAll() throws SQLException {
        ArrayList<Diplomes> listDip = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT CODEDIP, INTITULEDIP FROM diplomes;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                Diplomes dip = new Diplomes();
                dip.setCODEDIP(result.getInt("CODEDIP"));
                dip.setINTITULEDIP(result.getString("INTITULEDIP"));                
                listDip.add(dip);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDip;
    }
    
    public Diplomes getOne(int codeDip) throws SQLException {
        Diplomes dip = null;
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM diplomes WHERE CODEDIP = '"+ codeDip +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                dip = new Diplomes();
                dip.setCODEDIP(result.getInt("CODEDIP"));
                dip.setINTITULEDIP(result.getString("INTITULEDIP"));
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return dip;
    }
}
