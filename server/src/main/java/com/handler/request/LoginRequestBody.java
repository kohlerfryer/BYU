package com.familymap;

import com.familymap.UserAccess;
import com.familymap.InvalidRequestException;
import static com.familymap.Util.*;
import java.util.ArrayList;


// public interface RequestBody{
//     private validateInputs();
// }

public class LoginRequestBody{
    private String userName;
    private String password;

    //do to wierd BYU api standards, username must be userName
    public LoginRequestBody(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
        ArrayList<User> userList = userAccess.get("username", "=", this.userName);
        if(userList.size() == 0) throw new InvalidRequestException("invalid username"); 
        if(!userList.get(0).getPassword().equals(Util.getHash(this.password))) throw new InvalidRequestException("invalid password"); 
    }
     
    public String getUsername(){
        return this.userName;
    }
    public String getPassword(){
        return this.password;
    }

}