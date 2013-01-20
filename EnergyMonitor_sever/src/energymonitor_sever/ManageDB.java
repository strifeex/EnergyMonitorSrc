/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package energymonitor_sever;

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
   private ResultSet rs;
   public void ManageDB(){
       try{
             //load Class driver
             Class.forName(ManageDB.JDBC_DRIVER);
       }
       catch(Exception ex){
             JOptionPane.showConfirmDialog((Component) null, "ManageDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
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
            JOptionPane.showConfirmDialog((Component) null, "ConnDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            res = false;
        }
        return res;
    }   
   
   public boolean CheckDB(String serverIP,String NameDB){
        this.res = true;
        try{
        //Connect db
        this.url = "jdbc:mysql://" + serverIP + "/"+NameDB;
        this.conn= DriverManager.getConnection(this.url, this.User, this.Pass);
        this.stmt = this.conn.createStatement();              
        }
        catch(Exception ex){
            JOptionPane.showConfirmDialog((Component) null, "CheckDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
            res = false;
        }
        return res;
    }
   
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
           JOptionPane.showConfirmDialog((Component) null, "CreatDB = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
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
                   + "Id int NOT NULL AUTO_INCREMENT, "
                   + "MAC varchar(50) , "
                   + "name varchar(255) , "
                   + "detail varchar(255) , "
                   + "time TIMESTAMP , "
                   + "status ENUM('START','END') , "
                   + "lost_min int , "
                   + "start_time TIMESTAMP , "
                   + "watt int , "
                   + "cost_unit float ,"
                   + "PRIMARY KEY (Id) "
                   + ") ";
           this.stmt.executeUpdate(this.sql);
           
           this.sql = "CREATE TABLE sever_info "
                   + "( "
                   + "Id int NOT NULL AUTO_INCREMENT, "
                   + "MAC varchar(50) , "
                   + "delay int , "
                   + "cost_per_unit float , "
                   + "time TIMESTAMP , "
                   + "PRIMARY KEY (Id) "
                   + ") ";
           this.stmt.executeUpdate(this.sql);
           
           this.sql = "CREATE TABLE client_online "
                   + "( "
                   + "Id int , "
                   + "MAC varchar(50) , "
                   + "name varchar(255) , "
                   + "time TIMESTAMP , "
                   + "online_min int , "
                   + "start_time TIMESTAMP , "
                   + "PRIMARY KEY (Id) "
                   + ") ";
           this.stmt.executeUpdate(this.sql);
           
           this.sql = "INSERT INTO sever_info(Id,MAC,delay,cost_per_unit) "
                   + "VALUES(1 , 'MAC_INITIAL' , 1 , 3) ";
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           JOptionPane.showConfirmDialog((Component) null, "Createtable = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           res = false;
       }
       return res;

   }
   public void setMysqlPrivileges(){
       String command = "mysql -u root --password=1234 ";
       command += "--execute=";
       command += '"';
       command += "GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '1234' WITH GRANT OPTION;";
       command += '"';
       try {
           Process child = Runtime.getRuntime().exec(command);
       } catch (Exception ex) {
           JOptionPane.showConfirmDialog((Component) null, "setMysqlPrivileges  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           
       }
   }
   
   public void insertData(String sql){
       try{
           this.stmt = this.conn.createStatement();
           this.sql = sql;
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           JOptionPane.showConfirmDialog((Component) null, "insertData  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           ex.printStackTrace();
       }
   }
   
      public void updateData(String sql){
       try{
           this.stmt = this.conn.createStatement();
           this.sql = sql;
           this.stmt.executeUpdate(this.sql);
           System.out.println(sql);
       }
       catch(Exception ex){
           JOptionPane.showConfirmDialog((Component) null, "updateData  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           ex.printStackTrace();
       }
   }
   public int getDelay(){
       
       try {
           
           this.sql = "select delay from sever_info";
           this.rs = this.stmt.executeQuery(this.sql);
           this.rs.next(); 
           return Integer.parseInt(this.rs.getString("delay"));
       } catch (Exception ex) {
           JOptionPane.showConfirmDialog((Component) null, "getDelay  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           ex.printStackTrace();
           return 0;
       }
   }
   
   public int getCurrentid(String mac_address){

       try {
           this.sql = "select MAX(id) from client_info where MAC = '"+mac_address+"'";
           this.rs = this.stmt.executeQuery(this.sql);
           this.rs.next();
           return Integer.parseInt(this.rs.getString(1));
       } catch (Exception ex) {
           JOptionPane.showConfirmDialog((Component) null, "getCurrentid  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           //System.out.println(ex.toString()+" getCuurErr");
           //System.out.println(rs.toString());
           return 0;
       }
   }
   
   public ResultSet getSelectdata(String sql){

       try {
           this.rs = this.stmt.executeQuery(sql);
           return this.rs;
       } catch (Exception ex) {
           JOptionPane.showConfirmDialog((Component) null, "getSelectdata  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           ex.printStackTrace();
           
           return this.rs;
           //rs = null;
       }      
       
   }
   
   public void closeDB(){
       if(this.conn != null){
           try {
               this.conn.close();
               this.stmt.close();
           }
           catch(Exception ex){ 
               JOptionPane.showConfirmDialog((Component) null, "closeDB  = "+ex.toString(), "alert", JOptionPane.DEFAULT_OPTION);
           }          
       }
   }
   
}

    
