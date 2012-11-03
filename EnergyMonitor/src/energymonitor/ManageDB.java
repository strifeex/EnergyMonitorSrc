
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

    
