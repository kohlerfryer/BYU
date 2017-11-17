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

public class RegisterService {
//
//    private String stringifyJsonObject(Object object){
////        JsonObject response = new JsonObject();
////        response.addProperty("message", this.message);
//        return gson.toJson(object);
//    }

    //TODO -- make response objects
    public static void register(String serverHost, String serverPort, RegisterRequestBody requestBody, Context context){
        Log.d("asdf", "http://"+serverHost+":"+serverPort+"/user/register");
        Gson gson = new Gson();
        Consumer<String> success = (data) -> {
            JsonObject response = new JsonParser().parse(data).getAsJsonObject();
            PersonRequestBody personRequestBody = new PersonRequestBody(response.get("authToken").getAsString(), response.get("personID").getAsString());
            PersonService.get(personRequestBody, context);
        };
        Consumer<String> failure = (data) -> {
            JsonObject response = new JsonParser().parse(data).getAsJsonObject();
            Toast.makeText(context, response.get("message").getAsString(), 30000).show();
        };
        String stringifiedRequestBody = gson.toJson(requestBody);

        PostRequest loginRequest = new PostRequest(stringifiedRequestBody, "application/json", success, failure, null);
        try{
            URL url = new URL("http://"+serverHost+":"+serverPort+"/user/register");
            loginRequest.execute(url);
        }catch(Exception e){

        }
    }
}
