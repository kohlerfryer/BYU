package familymapapp.Request;

public class LoginRequestBody {
    private String userName;
    private String password;

    public LoginRequestBody(String userName, String password){
        this.password = password;
        this.userName = userName;
    }

    public String getPassword(){
        return this.password;
    }
    public String getUserName(){return this.userName;}

}