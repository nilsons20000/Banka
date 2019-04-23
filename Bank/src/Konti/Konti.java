package Konti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nils
 */
public class Konti {
    int id;
    public String kredita_numurs;
    public  int balance;
    public String nosaukums;
    Konti(){

    }
    
    public Konti(int ID, String Kredita_Numurs, int Balance){
        this.id=ID;
        this.kredita_numurs=Kredita_Numurs;
        this.balance=Balance;
    }
    public Konti(int ID, String Kredita_Numurs, int Balance,String Table_Name){
        this.id=ID;
        this.kredita_numurs=Kredita_Numurs;
        this.balance=Balance;
        this.nosaukums=Table_Name;
    }
 
    public int getID(){
        return id;
    }
    public String getKredita_Numurs(){
        return kredita_numurs;
    }
    public int getBalance(){
        return balance;
    }
    public String getTable_Name(){
        return nosaukums;
    }
    
}

