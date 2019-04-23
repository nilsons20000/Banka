package Konti;

import java.sql.ResultSet;
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
public class Kredita_Uzkrajuma_Algas_ADD extends Konti{
     public String Insert_Kont(String konta_name,String Konta_Nosaukums,String ID,String Konta_numurs,int Balance){
        String query;
        query = "INSERT INTO "+Konta_Nosaukums+"(`id`, `Konta_numurs`, `Balance`,`Nosaukums`) VALUES ('"+ID+"','"+Konta_numurs+"','"+Balance+"',+'"+konta_name+"')";
        return query;
       
    }
     
     
     
}
