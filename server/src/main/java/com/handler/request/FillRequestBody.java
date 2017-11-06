package com.familymap;

import com.familymap.UserAccess;
import com.familymap.InvalidRequestException;
import static com.familymap.Util.*;
import java.util.ArrayList;


// public interface RequestBody{
//     private validateInputs();
// }

public class FillRequestBody{
    private String userName;
    private int generationCount;
    private final int defaultGenerationCount = 4;

    //do to wierd BYU api standards, username must be userName
    public FillRequestBody(String userName, int generationCount){
        this.userName = userName;
        this.generationCount = generationCount;
    }
    public FillRequestBody(String userName){
        this.userName = userName;
    }

    public void validate(UserAccess userAccess) throws InvalidRequestException{
        ArrayList<User> userList = userAccess.get("username", "=", this.userName);
        if(userList.size() == 0) throw new InvalidRequestException("invalid username"); 
        if(generationCount < 0) throw new InvalidRequestException("invalid generation count"); 
    }

     
    public String getUsername(){
        return this.userName;
    }
    public int getGenerationCount(){
        //JVM intitalizes gc to zero if 
        //it is not initialzed elsewhere
        if(this.generationCount == 0){
            return this.defaultGenerationCount;
        }else{
            return this.generationCount;
        }    
    }

}