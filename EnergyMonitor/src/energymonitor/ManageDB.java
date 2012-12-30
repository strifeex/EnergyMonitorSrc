
package energymonitor;

import java.awt.Component;
import java.sql.*;
import javax.swing.JOptionPane;

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
   private boolean res = true;
   public void ManageDB(){
       try{
             //load Class driver
             Class.forName(ManageDB.JDBC_DRIVER);
       }
       catch(Exception ex){
           //JOptionPane.showConfirmDialog((Component) null, "ManageDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
             ex.printStackTrace();
       }
   }

   public boolean ConnDB(String serverIP){
        this.res = true;
        try{
        //Connect db
        this.url = "jdbc:mysql://" + serverIP ;
        this.conn= DriverManager.getConnection(this.url, this.User, this.Pass);
                      
        }
        catch(Exception ex){
            //JOptionPane.showConfirmDialog((Component) null, "ConnDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            ex.printStackTrace();
            res = false;
        }
        return res;
    }   
   
    public boolean CheckDB(String serverIP, String NameDB) {
        this.res = true;
        try {
            //Connect db
            this.url = "jdbc:mysql://" + serverIP + "/" + NameDB;
            this.conn = DriverManager.getConnection(this.url, this.User, this.Pass);

        } catch (Exception ex) {
            //JOptionPane.showConfirmDialog((Component) null, "CheckDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            res = false;
        }
        return res;
    }
  /* not use in client  but use in sever    
   public boolean CreatDB(){
       this.res = true;
       url = "jdbc:mysql://localhost/";
       try{
           this.conn = DriverManager.getConnection(this.url, this.User, this.Pass);
           this.stmt = this.conn.createStatement();
           this.sql = "CREATE DATABASE EnergyMonitor";
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
            res = false;
       }
       return res;
   }
 
   public boolean Createtable(String serverIP,String NameDB){
       this.res = true;
       this.url = "jdbc:mysql://" + serverIP + "/"+NameDB;
       try{
           this.conn = DriverManager.getConnection(this.url, this.User, this.Pass);
           this.stmt = this.conn.createStatement();
           this.sql = "CREATE TABLE client_info "
                   + "( "
                   + "Id int , "
                   + "MAC varchar(50) , "
                   + "name varchar(255) , "
                   + "detail varchar(255) , "
                   + "time TIMESTAMP , "
                   + "status ENUM('START','END') , "
                   + "lost_min int , "
                   + "start_time TIMESTAMP , "
                   + "cost_unit int , "
                   + "PRIMARY KEY (Id) "
                   + ") ";
           this.stmt.executeUpdate(this.sql);
           
           this.sql = "CREATE TABLE sever_info "
                   + "( "
                   + "Id int , "
                   + "MAC varchar(50) , "
                   + "delay int , "
                   + "cost_per_unit int , "
                   + "time TIMESTAMP , "
                   + "PRIMARY KEY (Id) "
                   + ") ";
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           res = false;
       }
       return res;

   }
   */
   public void insertData(String sql){

       try{
           this.stmt = this.conn.createStatement();
           this.sql = sql;
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           JOptionPane.showConfirmDialog((Component) null, "insertData = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           ex.printStackTrace();
       }
   }
   
    public void updateData(String sql) {

        try {
            this.stmt = this.conn.createStatement();
            this.sql = sql;
            this.stmt.executeUpdate(this.sql);
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog((Component) null, "updateData = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            ex.printStackTrace();
        }
    }
      
   public int getDelay(){
       
       try {
           this.stmt = this.conn.createStatement();
           this.sql = "select delay from sever_info";
           ResultSet rs = this.stmt.executeQuery(sql);
           rs.next();
           return Integer.parseInt(rs.getString("delay"));
       } catch (Exception ex) {
           JOptionPane.showConfirmDialog((Component) null, "getDelay = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           return 0;
       }
   }
   
    public float getCost_unit() {

        try {
            this.stmt = this.conn.createStatement();
            this.sql = "select cost_per_unit from sever_info";
            ResultSet rs = this.stmt.executeQuery(sql);
            rs.next();
            return Float.parseFloat(rs.getString(1));
        } catch (Exception ex) {
            JOptionPane.showConfirmDialog((Component) null, "getCost_unit = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            return 0;
        }
    }
   
   public int getCurrentid(String mac_address){

       try {
           this.sql = "select MAX(id) from client_info where MAC = '"+mac_address+"'";
           ResultSet rs = this.stmt.executeQuery(sql);
           rs.next();
           return Integer.parseInt(rs.getString(1));
       } catch (Exception ex) {
           JOptionPane.showConfirmDialog((Component) null, "getCurrentid = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           return 0;
       }
   }
   public void closeDB(){
       if(this.conn != null){
           try {
               this.conn.close();
               this.stmt.close();
           }
           catch(Exception ex){ 
               JOptionPane.showConfirmDialog((Component) null, "closeDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           }          
       }
   }
   
}

    
