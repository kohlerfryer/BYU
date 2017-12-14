package familymapapp.Service;


import java.net.URL;
import java.util.function.Consumer;

import familymapapp.Proxy.PostRequest;
import familymapapp.Request.LoginRequestBody;
import familymapapp.Util.Util;

public class LoginService {

    private static final String slug = "/user/login";

    public static void login(String serverHost, String serverPort, LoginRequestBody requestBody, Consumer<String> success, Consumer<String> failure){

        String stringifiedRequestBody = Util.covertObjectToJsonString(requestBody);

        PostRequest loginRequest = new PostRequest(stringifiedRequestBody, "application/json", success, failure, null);
        try{
            URL url = new URL("http://"+serverHost+":"+serverPort+slug);
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }
}
