/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package energymonitor_sever;


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
   private boolean res = true;
   public void ManageDB(){
       try{
             //load Class driver
             Class.forName(ManageDB.JDBC_DRIVER);
       }
       catch(Exception ex){
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
                      
        }
        catch(Exception ex){
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
   
   public void insertData(String sql){
       try{
           this.stmt = this.conn.createStatement();
           this.sql = sql;
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
   }
   
      public void updateData(String sql){
       try{
           this.stmt = this.conn.createStatement();
           this.sql = sql;
           this.stmt.executeUpdate(this.sql);
       }
       catch(Exception ex){
           ex.printStackTrace();
       }
   }
   public int getDelay(){
       
       try {
           this.sql = "select delay from sever_info";
           ResultSet rs = this.stmt.executeQuery(sql);
           return Integer.parseInt(rs.getString("delay"));
       } catch (Exception ex) {
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
           ex.printStackTrace();
           return 0;
       }
   }
   
   public ResultSetMetaData getSelectdata(String sql){
       ResultSetMetaData rs = null;
       try {
           rs = this.stmt.executeQuery(sql).getMetaData();
       } catch (Exception ex) {
           rs = null;
       }      
       return rs;
   }
   
   public void closeDB(){
       if(this.conn != null){
           try {
               this.conn.close();
               this.stmt.close();
           }
           catch(Exception ex){ 
           }          
       }
   }
   
}

    