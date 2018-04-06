/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package Utils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author qsole
 */
public class Utils {
    public static Connection connect(){
        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/bdd_gme","root","");
            
        } catch (Exception e) {
            //TODO
        }
        return c;
    }
    public static void disconnect(Connection c){
        try {
            c.close();
        } catch (Exception e) {
            //TODO
        }
    }
    public static ResultSet query(Connection c, String sql) throws SQLException{
        ResultSet r = null;
        try {
            java.sql.Statement s = c.createStatement();
            r = s.executeQuery(sql);
            //s.close();
        } catch (Exception e) {
            //TODO
        }
        return r;
    }
    public static void update(Connection c, String sql) throws SQLException{
        try {
            java.sql.Statement s = c.createStatement();
            s.executeUpdate(sql);
            s.close();
        } catch (Exception e) {
            //TODO
        }
    }
}
