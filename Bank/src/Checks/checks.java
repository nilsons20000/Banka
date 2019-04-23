package Checks;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */import java.sql.*;
import java.security.*;
import java.math.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Nils
 */
public class checks {
     private Connection con;
    private Statement st;
    private ResultSet rs;
     
    public boolean passwordCHECK(String password,String old_pass) throws NoSuchAlgorithmException{
     if((password == null) || (password.isEmpty()) ||  password.equals(old_pass)){
       return false;
     }else{
           return true;
        }
    }
    
     public boolean passwordCHECK(String password) throws NoSuchAlgorithmException{
      if((password == null) || (password.isEmpty()) ){
       return false;
     }else{
        if(password.length()>8 && password.length()<=20 )
           return true;
        }
        return false;
    }
    public String passwordMD(String password) throws NoSuchAlgorithmException {  
        String s=password;
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        String PassMD;
        PassMD = new BigInteger(1,m.digest()).toString(16);
        return PassMD; 
    }
    
    public boolean isValidword(String word) {
           String ePattern = "^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(word);
           return m.matches();
    } 
    public boolean isValidEmail(String email){
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{1,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }
    public String firstUpperCase(String word){
	return word.substring(0, 1).toUpperCase() +word.substring(1).toLowerCase();
    }
    

    public boolean checkField(String word){
	if((word == null) || (word.isEmpty())) {
            return false;
        }else 
            return true;
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    
     public boolean hasField(String temp,String nosaukums,String check_name)   {
        try{
                con = DriverManager.getConnection("jdbc:mysql://localhost/users?serverTimezone=Europe/Riga&useSSL=false","root","");
                st=con.createStatement();
                String check =temp ;
                String sql = "SELECT * FROM "+nosaukums+" WHERE "+check_name+"= ?";
                PreparedStatement preparedStatement =con.prepareStatement(sql);
                preparedStatement.setString(1, check);
                ResultSet rs = preparedStatement.executeQuery();
                
        return rs.next();
        
            }catch(SQLException ex){
                ex.printStackTrace();

            }   
        
        return false;
    }
    
}
