package com.familymap;

/**
* PersonEvent a single User tuple
*/
public class User{

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

    /** users hashed password */
    private String personId;

    public User(String username, String email, String firstName, String lastName, String gender, String password, String personId){
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
        this.personId = personId;
    }
    
    public String getUsername(){
        return this.username;
    }
    public String getEmail(){
        return this.email;
    }
   public String getFirstName(){
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
   public String getPersonId(){
        return this.personId;
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
    public void setPersonId(String personId){
        this.personId = personId;
    }
}











