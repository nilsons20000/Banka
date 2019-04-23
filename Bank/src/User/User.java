package User;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nils
 */
public class User  {
    private final int id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    

    public User(int ID, String FirstName, String LastName,String Email, String Password){
        this.id=ID;
        this.firstName=FirstName;
        this.lastName=LastName;
        this.email=Email;
        this.password=Password;
    }

  
    public int getID(){
        return id;
    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
  
   
}
