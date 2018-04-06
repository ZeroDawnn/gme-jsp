/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utils.Utils;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author julien
 */
public class DemandesMobilites {
    private int REFDEMMOB;
    private int NUMETUDIANT;
    private int CODEDIP;
    private Date DATEDEPOTDEMMOB;
    private String ETATDEMMOB;

    public int getREFDEMMOB() {
        return REFDEMMOB;
    }

    public void setREFDEMMOB(int REFDEMMOB) {
        this.REFDEMMOB = REFDEMMOB;
    }

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

    public Date getDATEDEPOTDEMMOB() {
        return DATEDEPOTDEMMOB;
    }

    public void setDATEDEPOTDEMMOB(Date DATEDEPOTDEMMOB) {
        this.DATEDEPOTDEMMOB = DATEDEPOTDEMMOB;
    }

    public String getETATDEMMOB() {
        return ETATDEMMOB;
    }

    public void setETATDEMMOB(String ETATDEMMOB) {
        this.ETATDEMMOB = ETATDEMMOB;
    }
    
    public static ArrayList<DemandesMobilites> getAll() throws SQLException {
        ArrayList<DemandesMobilites> listDemMob = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesmobilites\n" +
                     "   INNER JOIN etudiants ON demandesmobilites.NUMETUDIANT = etudiants.NUMETUDIANT\n" +
                     "   INNER JOIN diplomes ON demandesmobilites.CODEDIP = diplomes.CODEDIP;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesMobilites demmob = new DemandesMobilites();
                demmob.setREFDEMMOB(result.getInt("REFDEMMOB"));
                demmob.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                demmob.setCODEDIP(result.getInt("CODEDIP"));
                demmob.setDATEDEPOTDEMMOB(result.getDate("DATEDEPOTDEMMOB"));
                demmob.setETATDEMMOB(result.getString("ETATDEMMOB"));                
                listDemMob.add(demmob);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemMob;
    }
    
    public static ArrayList<DemandesMobilites> getAllByStudent(int numEtu) throws SQLException {
        ArrayList<DemandesMobilites> listDemMob = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesmobilites WHERE NUMETUDIANT = '"+ numEtu +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesMobilites demmob = new DemandesMobilites();
                demmob.setREFDEMMOB(result.getInt("REFDEMMOB"));
                demmob.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                demmob.setCODEDIP(result.getInt("CODEDIP"));
                demmob.setDATEDEPOTDEMMOB(result.getDate("DATEDEPOTDEMMOB"));
                demmob.setETATDEMMOB(result.getString("ETATDEMMOB"));                
                listDemMob.add(demmob);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemMob;
    }
    
    public static ArrayList<DemandesMobilites> getAllByUniv(int codeUni) throws SQLException {
        ArrayList<DemandesMobilites> listDemMob = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesmobilites INNER JOIN diplomes ON demandesmobilites.CODEDIP = diplomes.CODEDIP WHERE diplomes.CODEU = '"+ codeUni +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesMobilites demmob = new DemandesMobilites();
                demmob.setREFDEMMOB(result.getInt("REFDEMMOB"));
                demmob.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                demmob.setCODEDIP(result.getInt("CODEDIP"));
                demmob.setDATEDEPOTDEMMOB(result.getDate("DATEDEPOTDEMMOB"));
                demmob.setETATDEMMOB(result.getString("ETATDEMMOB"));                
                listDemMob.add(demmob);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemMob;
    }
    
    public static ArrayList<DemandesMobilites> getAllByDiploma(int codeDip) throws SQLException {
        ArrayList<DemandesMobilites> listDemMob = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesmobilites WHERE CODEDIP = '"+ codeDip +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesMobilites demmob = new DemandesMobilites();
                demmob.setREFDEMMOB(result.getInt("REFDEMMOB"));
                demmob.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                demmob.setCODEDIP(result.getInt("CODEDIP"));
                demmob.setDATEDEPOTDEMMOB(result.getDate("DATEDEPOTDEMMOB"));
                demmob.setETATDEMMOB(result.getString("ETATDEMMOB"));                
                listDemMob.add(demmob);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemMob;
    }
    
    public static DemandesMobilites getDemMob(int ref) throws SQLException {
        DemandesMobilites demmob = null;
        Connection conn = Utils.connect();        
        ResultSet result = Utils.query(conn, "SELECT * FROM demandesmobilites WHERE REFDEMMOB = '"+ref+"'");
        try {
            while(result.next()) {
                demmob = new DemandesMobilites();
                demmob.setREFDEMMOB(result.getInt("REFDEMMOB"));
                demmob.setNUMETUDIANT(result.getInt("NUMETUDIANT"));
                demmob.setCODEDIP(result.getInt("CODEDIP"));
                demmob.setDATEDEPOTDEMMOB(result.getDate("DATEDEPOTDEMMOB"));
                demmob.setETATDEMMOB(result.getString("ETATDEMMOB"));
            }
            
            result.close();
        } catch (SQLException ex) {
            //TODO
        }
        Utils.disconnect(conn);
        return demmob;
    }
    
    public boolean add() {
        boolean result = false;
        Connection conn = Utils.connect();
        String sql = "INSERT INTO demandesmobilites VALUES(null, ?, ?, ?, ?)";
        try {
            // On prépare notre requête
            PreparedStatement prepared = conn.prepareStatement(sql);
            // On assigne nos paramètres
            prepared.setInt(1, this.NUMETUDIANT);
            prepared.setInt(2, this.CODEDIP);
            prepared.setDate(3, this.DATEDEPOTDEMMOB);
            prepared.setString(4, this.ETATDEMMOB);
            
            // Si on a 0 insert c'est que ça n'a pas fonctionné
            int nbInsert = prepared.executeUpdate();
            if(nbInsert > 0) result = true;
            prepared.close();
        } catch (SQLException ex) {
            result = false;
        }
        Utils.disconnect(conn);
        return result;
    }
    
    public boolean update(int ref) {
        boolean result = false;
        Connection conn = Utils.connect();
        String sql = "UPDATE demandesmobilites set ETATDEMMOB = ? WHERE REFDEMMOB = ?;";
        try {
            // On prépare notre requête
            PreparedStatement prepared = conn.prepareStatement(sql);
            // On assigne nos paramètres
            prepared.setString(1, this.ETATDEMMOB);
            prepared.setInt(2, ref);
            
            // Si on a 0 insert c'est que ça n'a pas fonctionné
            int nbUpdate = prepared.executeUpdate();
            if(nbUpdate > 0) result = true;
            prepared.close();
        } catch (SQLException ex) {
            result = false;
        }
        Utils.disconnect(conn);
        return result;
    }
    
    public static boolean delete(int ref) {
        boolean result = false;
        Connection conn = Utils.connect();
        String sql = "DELETE FROM demandesmobilites WHERE REFDEMMOB = ?;";
        try {
            // On prépare notre requête
            PreparedStatement prepared = conn.prepareStatement(sql);
            // On assigne nos paramètres
            prepared.setInt(1, ref);
            
            // Si on a 0 insert c'est que ça n'a pas fonctionné
            int nbDelete = prepared.executeUpdate();
            if(nbDelete > 0) result = true;
            prepared.close();
        } catch (SQLException ex) {
        }
        Utils.disconnect(conn);
        return result;
    }
}
