package familymapapp.HTTP;

/**
 * Created by kittykatt on 11/14/17.
 */

public class HTTPResponse {
    private final boolean success;
    private final String response;

    HTTPResponse(boolean success, String response){
        this.success = success;
        this.response = response;
    }

    public boolean wasSuccessfull(){
        return this.success;
    }

    public String getResponse(){
        return this.response;
    }

}
