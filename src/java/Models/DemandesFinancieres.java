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

/**
 *
 * @author julien
 */
public class DemandesFinancieres {
    private int REFDEMFIN;
    private int IDCONTRAT;
    private Date DATEDEPOTDEMFIN;
    private double MONTANTACCORDE;
    private String ETATDEMFIN;

    public int getREFDEMFIN() {
        return REFDEMFIN;
    }

    public void setREFDEMFIN(int REFDEMFIN) {
        this.REFDEMFIN = REFDEMFIN;
    }

    public int getIDCONTRAT() {
        return IDCONTRAT;
    }

    public void setIDCONTRAT(int IDCONTRAT) {
        this.IDCONTRAT = IDCONTRAT;
    }

    public Date getDATEDEPOTDEMFIN() {
        return DATEDEPOTDEMFIN;
    }

    public void setDATEDEPOTDEMFIN(Date DATEDEPOTDEMFIN) {
        this.DATEDEPOTDEMFIN = DATEDEPOTDEMFIN;
    }

    public double getMONTANTACCORDE() {
        return MONTANTACCORDE;
    }

    public void setMONTANTACCORDE(double MONTANTACCORDE) {
        this.MONTANTACCORDE = MONTANTACCORDE;
    }

    public String getETATDEMFIN() {
        return ETATDEMFIN;
    }

    public void setETATDEMFIN(String ETATDEMFIN) {
        this.ETATDEMFIN = ETATDEMFIN;
    }
    
    public static ArrayList<DemandesFinancieres> getAll() throws SQLException {
        ArrayList<DemandesFinancieres> listDemFin = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesfinancieres;";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesFinancieres demfin = new DemandesFinancieres();
                demfin.setREFDEMFIN(result.getInt("REFDEMFIN"));
                demfin.setIDCONTRAT(result.getInt("IDCONTRAT"));
                demfin.setDATEDEPOTDEMFIN(result.getDate("DATEDEPOTDEMFIN"));
                demfin.setMONTANTACCORDE(result.getDouble("MONTANTACCORDE"));
                demfin.setETATDEMFIN(result.getString("ETATDEMFIN"));
                listDemFin.add(demfin);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemFin;
    }
    
    public static ArrayList<DemandesFinancieres> getAllByContract(int idCon) throws SQLException {
        ArrayList<DemandesFinancieres> listDemFin = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesfinancieres WHERE IDCONTRAT = '"+ idCon +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesFinancieres demfin = new DemandesFinancieres();
                demfin.setREFDEMFIN(result.getInt("REFDEMFIN"));
                demfin.setIDCONTRAT(result.getInt("IDCONTRAT"));
                demfin.setDATEDEPOTDEMFIN(result.getDate("DATEDEPOTDEMFIN"));
                demfin.setMONTANTACCORDE(result.getDouble("MONTANTACCORDE"));
                demfin.setETATDEMFIN(result.getString("ETATDEMFIN"));               
                listDemFin.add(demfin);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemFin;
    }
    
    public static ArrayList<DemandesFinancieres> getAllByProgram(int idP) throws SQLException {
        ArrayList<DemandesFinancieres> listDemFin = new ArrayList<>();
        Connection conn = Utils.connect();
        String sql = "SELECT * FROM demandesfinancieres INNER JOIN contrats ON demandesfinancieres.IDCONTRAT = contrats.IDCONTRAT INNER JOIN programmes ON contrats.IDP = programmes.IDP WHERE programmes.IDP = '"+ idP +"';";
        ResultSet result = Utils.query(conn, sql);
        
        try {
            while(result.next()) {
                DemandesFinancieres demfin = new DemandesFinancieres();
                demfin.setREFDEMFIN(result.getInt("REFDEMFIN"));
                demfin.setIDCONTRAT(result.getInt("IDCONTRAT"));
                demfin.setDATEDEPOTDEMFIN(result.getDate("DATEDEPOTDEMFIN"));
                demfin.setMONTANTACCORDE(result.getDouble("MONTANTACCORDE"));
                demfin.setETATDEMFIN(result.getString("ETATDEMFIN"));               
                listDemFin.add(demfin);
            }
            
            result.close();
        } catch (SQLException ex) {
            // TODO
        }
        Utils.disconnect(conn);
        return listDemFin;
    }
    
    public static DemandesFinancieres getDemFin(int ref) throws SQLException {
        DemandesFinancieres demfin = null;
        Connection conn = Utils.connect();        
        ResultSet result = Utils.query(conn, "SELECT * FROM demandesfinancieres WHERE REFDEMFIN = '"+ref+"'");
        try {
            while(result.next()) {
                demfin = new DemandesFinancieres();
                demfin.setREFDEMFIN(result.getInt("REFDEMFIN"));
                demfin.setIDCONTRAT(result.getInt("IDCONTRAT"));
                demfin.setDATEDEPOTDEMFIN(result.getDate("DATEDEPOTDEMFIN"));
                demfin.setMONTANTACCORDE(result.getDouble("MONTANTACCORDE"));
                demfin.setETATDEMFIN(result.getString("ETATDEMFIN"));
            }
            
            result.close();
        } catch (SQLException ex) {
            //TODO
        }
        Utils.disconnect(conn);
        return demfin;
    }
    
    public boolean add() {
        boolean result = false;
        Connection conn = Utils.connect();
        String sql = "INSERT INTO demandesfinancieres VALUES(null, ?, ?, ?, ?)";
        try {
            // On prépare notre requête
            PreparedStatement prepared = conn.prepareStatement(sql);
            // On assigne nos paramètres
            prepared.setInt(1, this.IDCONTRAT);
            prepared.setDate(2, this.DATEDEPOTDEMFIN);
            prepared.setDouble(3, this.MONTANTACCORDE);
            prepared.setString(4, this.ETATDEMFIN);
            
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
        String sql = "UPDATE demandesfinancieres set MONTANTACCORDE = ?, ETATDEMFIN = ? WHERE REFDEMFIN = '"+ref+"';";
        try {
            // On prépare notre requête
            PreparedStatement prepared = conn.prepareStatement(sql);
            // On assigne nos paramètres
            prepared.setDouble(1, this.MONTANTACCORDE);
            prepared.setString(2, this.ETATDEMFIN);
            
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
        String sql = "DELETE FROM demandesfinancieres WHERE REFDEMFIN = ?;";
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
