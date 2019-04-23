package Checks;


import User.User;
import DBconnect.DBConnect;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nils
 */
public class LogIN_Check {
    Connection con;
    Statement st;
    ResultSet rs;
    DBConnect connect = new DBConnect();
    
    public ArrayList<User> getusersList(String nosaukums){
        ArrayList<User> usersList=new ArrayList<>();
        connect.DBConnect();
        
        String query="Select *From "+nosaukums;
               
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/Users?serverTimezone=Europe/Riga&useSSL=false","root","");
            st=con.createStatement();
             rs = st.executeQuery(query);
             User user;
             while(rs.next()){
                 user=new User(rs.getInt("id"),rs.getString("firstName"),rs.getString("lastName"),rs.getString("email"),rs.getString("password"));
                 usersList.add(user);
             }
            con.close();
            st.close();
            rs.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return usersList;
    }
    
    public boolean LogIN_Check_Clients(String nosaukums,double id,String Password) throws NoSuchAlgorithmException{
            checks checks=new checks();
            ArrayList<User> list = getusersList(nosaukums);
            Password=checks.passwordMD(Password);
            
            for (int i=0;i<list.size();i++){
                   if ((Password.equals(list.get(i).getPassword())) && (id==list.get(i).getID()))
                       return true;
            }
        return false;
    }
    

}
