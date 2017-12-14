package familymapapp.Request;

public class PersonRequestBody {
    private String personId;
    private String authenticationToken;

    public PersonRequestBody(String authenticationToken, String personId){
        this.personId = personId;
        this.authenticationToken = authenticationToken;
    }
    public PersonRequestBody(String authenticationToken){
        this.authenticationToken = authenticationToken;
    }

    public String getPersonId(){
        return this.personId;
    }
    public String getAuthenticationToken(){
        return this.authenticationToken;}
    }