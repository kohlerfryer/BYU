package com.familymap;

import com.familymap.UserAccess;
import com.familymap.InvalidRequestException;
import java.util.ArrayList;

// public interface RequestBody{
//     private validateInputs();
// }

public class RegisterRequestBody{
    private String userName;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String gender;

    //do to wierd BYU api standards, username must be userName
    public RegisterRequestBody(String userName, String password, String email, String firstName, String lastName, String gender){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }
    public RegisterRequestBody(String userName, String password, String email, String firstName, String lastName){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
        ArrayList<User> userList = userAccess.get("username", "=", this.userName);
        if(userList.size() > 0) throw new InvalidRequestException("username taken");
        this.validateInitialization();
        if(this.gender!= null && !this.gender.equals("m") && !this.gender.equals("f")) throw new InvalidRequestException("invalid gender type");
    }

    public void validateInitialization() throws InvalidRequestException{
        if(
            this.userName == null
            ||
            this.password == null
            ||
            this.email == null
            || 
            this.firstName == null
            ||
            this.lastName == null
        )throw new InvalidRequestException("Missing parameters");
    }
     
    public String getUsername(){
        return this.userName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPassword(){
        return this.password;
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

}