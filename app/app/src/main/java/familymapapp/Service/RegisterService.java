package familymapapp.Service;


import java.net.URL;
import java.util.function.Consumer;

import familymapapp.HTTP.PostRequest;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.UTIL.Util;

public class RegisterService {

    //TODO -- make response objects
    public static void register(String serverHost, String serverPort, RegisterRequestBody requestBody, Consumer<String> success, Consumer<String> failure){
        String stringifiedRequestBody = Util.covertObjectToJsonString(requestBody);

        PostRequest loginRequest = new PostRequest(stringifiedRequestBody, "application/json", success, failure, null);
        try{
            URL url = new URL("http://"+serverHost+":"+serverPort+"/user/register");
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }
}
