package familymapapp.Service;


import java.net.URL;
import java.util.function.Consumer;

import familymapapp.Proxy.PostRequest;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.Util.Util;

public class RegisterService {

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
