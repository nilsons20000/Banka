package SuperAdmin;
import Admin.Admin_panel;


import DBconnect.DBConnect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nils
 */


public class SuperAdmin  {
    Admin_panel panel;
    DBConnect connect = new DBConnect();

    public SuperAdmin() {
        this.panel = new  Admin_panel();
    }

    
    
    public String Insert_USERS(String liet_nosaukums,String name_INPUT,String lastName_INPUT,String email_INPUT,String password_INPUT){
        String query;
        query = "INSERT INTO `users`."+liet_nosaukums+"(`firstName`, `lastName`, `email`,`password`) VALUES ('"+name_INPUT+"','"+lastName_INPUT+"','"+email_INPUT+"','"+password_INPUT+"')";
        return query;
    }
    public String Delete_Users(String liet_nosaukums,String ID){
        String query="DELETE FROM `users`."+liet_nosaukums+" WHERE id="+ID;
        return query;
    }
    public String Delete_Users_Konti(String nosaukums,int ID){
        String query="DELETE FROM `konti`."+nosaukums+" WHERE `id`="+ID;
        return query;
    }
    public String Delete_Konti(String nr,String nos){
           String query="DELETE FROM `konti`."+nos+" WHERE `Konta_numurs`='"+nr+"'";
           return query;
       }
     
    public String Update_Users(String liet_nosaukums,String name_INPUT,String lastName_INPUT,String email_INPUT,String password_INPUT,String ID){
        String query = "UPDATE `users`."+liet_nosaukums+" SET `firstName`='"+name_INPUT+"',`lastName`='"+lastName_INPUT+"',`email`='"+email_INPUT+"',`password`='"+password_INPUT+"' WHERE id = "+ID;
        return query;
    }
   
    public String Update_Konts(double balance,String K_numurs,String nosaukums){
        String query = "UPDATE `konti`."+nosaukums+" SET `balance`='"+balance+"' WHERE Konta_numurs = '"+K_numurs+"'";
        return query;
    }
    
      public String LOG(String ID,String text){
        String query="INSERT INTO `log`.`vesture`(`id`, `Darbiba`) VALUES ('"+ID+"','"+text+"')";
        return query;
    }
    
}
