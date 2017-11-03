package com.familymap;

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
    //String userJson = gson.toJson(userObject);  
     
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

    // private validateInputs() throws InvalidRequestException{
        //throw new InvalidRequestExpection("invalid parameters");
        //gson.toJson(dobj, new FileWriter("D:\\file.json"));
    // }


}