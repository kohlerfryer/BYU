package familymapapp.Service;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.util.function.Consumer;

import familymapapp.HTTP.PostRequest;
import familymapapp.Request.LoginRequestBody;
import familymapapp.Request.PersonRequestBody;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.UTIL.Util;

public class LoginService {

    private static final String slug = "/user/login";

    //TODO -- make server response objects
    //todo make server host and port a global variable
    public static void login(String serverHost, String serverPort, LoginRequestBody requestBody, Consumer<String> success, Consumer<String> failure){

        String stringifiedRequestBody = Util.covertObjectToJsonString(requestBody);

        PostRequest loginRequest = new PostRequest(stringifiedRequestBody, "application/json", success, failure, null);
        try{
            //TODO == abstract out http://" bla bla " ldfdf : server port
            URL url = new URL("http://"+serverHost+":"+serverPort+slug);
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }
}
