package com.familymap;

/**
* PersonEvent a single User tuple
*/
public class User{

    /** tuples unique identifier in relation */
    private String id;

    /** Username of user */
    private String username;

    /** email of user */
    private String email;

    /** first name of user */
    private String firstName;

    /** last name of user */
    private String lastName;

    /** gender of user */
    private String gender;

    /** users hashed password */
    private String password;

    public User(String id, String username, String email, String firstName, String lastName, String gender, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
    }
    
    public String getId(){
        return this.id;
    }
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
   public String firstName(){
        return this.firstName;
    }
   public String getLastName(){
        return this.lastName;
    }
   public String getGender(){
        return this.gender;
    }
   public String getPassword(){
        return this.password;
    }
   
   
    public void setUsername(String username){
        this.username = username;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFirstName(String username){
        this.username = username;
    }
    public void setLastName(String email){
        this.email = email;
    }
    public void setGender(String password){
        this.password = password;
    }
}











