
package energymonitor;

import java.sql.*;

public class ManageDB {
   // JDBC driver name
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
   private String url;
   //  Database credentials
   static String User = "root";
   static String Pass = "1234";
   
   private Connection conn = null;
   private Statement stmt = null;
   private String sql;
   
   public void ManageDB(){
       try{
             //load Class driver
             Class.forName(ManageDB.JDBC_DRIVER);
       }
       catch(Exception ex){
             ex.printStackTrace();
       }
   }
   
   private boolean CheckDB(String serverIP,String NameDB){
        boolean res = true;
        try{
        //Connect db
        this.url = "jdbc:mysql://" + serverIP + "/"+NameDB;
        this.conn= DriverManager.getConnection(this.url, this.User, this.Pass);
                      
        }
        catch(Exception ex){
            res = false;
        }
        return res;
    }
   
   public void CreatDB(){
       url = "jdbc:mysql://localhost/";
       try{
           this.conn = DriverManager.getConnection(this.url, this.User, this.Pass);
           this.stmt = this.conn.createStatement();
           this.sql = "CREATE DATABASE EnergyMonitor";
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           
       }
   }
}

    
