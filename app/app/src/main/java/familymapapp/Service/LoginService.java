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
    public static void login(String serverHost, String serverPort, LoginRequestBody requestBody, Context context){
        Consumer<String> success = (data) -> {
            PersonRequestBody personRequestBody = new PersonRequestBody(Util.getValueFromJson(data, "authToken"), Util.getValueFromJson(data, "personID"));
            PersonService.get(personRequestBody, context);
        };
        Consumer<String> failure = (data) -> {
            Toast.makeText(context, Util.getValueFromJson(data, "message"), 30000).show();
        };
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
