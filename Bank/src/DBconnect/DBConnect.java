package DBconnect;


import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nils
 */
public class DBConnect {
    private Connection con;
    private Statement st;
    private ResultSet rs;
    
    /**
     *
     * @return
     */
    public boolean DBConnect(){
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost/Users?serverTimezone=Europe/Riga&useSSL=false","root","");
           st=con.createStatement();
           return true;
        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }
    
     public void DBConnect_Konti(){
        try{
           con = DriverManager.getConnection("jdbc:mysql://localhost/Konti?serverTimezone=Europe/Riga&useSSL=false","root","");
           st=con.createStatement();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
}
