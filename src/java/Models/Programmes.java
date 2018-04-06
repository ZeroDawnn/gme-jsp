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
public class Programmes {
    private int IDP;
    private String INTITULEP;
    private String EXPLICATION;

    public int getIDP() {
        return IDP;
    }

    public void setIDP(int IDP) {
        this.IDP = IDP;
    }

    public String getINTITULEP() {
        return INTITULEP;
    }

    public void setINTITULEP(String INTITULEP) {
        this.INTITULEP = INTITULEP;
    }

    public String getEXPLICATION() {
        return EXPLICATION;
    }

    public void setEXPLICATION(String EXPLICATION) {
        this.EXPLICATION = EXPLICATION;
    }
    
    public static ArrayList<Programmes> getAll() throws SQLException {
        ArrayList<Programmes> listPro = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM programmes;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                Programmes pro = new Programmes();
                pro.setIDP(result.getInt("IDP"));
                pro.setINTITULEP(result.getString("INTITULEP"));
                pro.setEXPLICATION(result.getString("EXPLICATION"));
                listPro.add(pro);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listPro;
    }
}
