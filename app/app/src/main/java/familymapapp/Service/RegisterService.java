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
import familymapapp.Request.PersonRequestBody;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.UTIL.Util;

public class RegisterService {

    //TODO -- make response objects
    public static void register(String serverHost, String serverPort, RegisterRequestBody requestBody, Context context){
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
            URL url = new URL("http://"+serverHost+":"+serverPort+"/user/register");
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }
}
