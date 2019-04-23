/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vesture;

/**
 *
 * @author Nils
 */
public class LOG {
    private final int id;
    private final String darbiba;
    
    public LOG(int ID, String DARBIBA){
        this.id=ID;
        this.darbiba=DARBIBA; 
    }
    public int getID(){
        return id;
    }
    public String getDarbiba(){
        return darbiba;
    }
}
