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

    /** referrs to person of user account */
    private String personId;

    /** users hashed password */
    private String password;

    public User(String id, String username, String email, String personId, String password){
        this.id = id;
        this.username = username;
        this.email = email;
        this.personId = personId;
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
    public String getPersonId(){
        return this.personId;
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
    public void setPersonId(String personId){
        this.personId = personId;
    }
   public void setPassword(String password){
        this.password = password;
    }
}











