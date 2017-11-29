package familymapapp.Service;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.util.function.Consumer;

import familymapapp.HTTP.GetRequest;
import familymapapp.HTTP.PostRequest;
import familymapapp.Request.PersonRequestBody;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.UTIL.Util;

public class PersonService {

    //TODO -- make response objects
    public static void get(PersonRequestBody requestBody, Context context){
        JsonObject person;
        //todo make model for person -- -make all service classes unison and clean :_
        Consumer<String> success = (data) -> {
            Toast.makeText(context, "firstname: " + Util.getValueFromJson(data, "firstName") + "\n lastname: " + Util.getValueFromJson(data, "lastname"), 30000).show();
        };
        Consumer<String> failure = (data) -> {
            JsonObject response = new JsonParser().parse(data).getAsJsonObject();
            Toast.makeText(context, response.get("message").getAsString(), 30000).show();
        };
        GetRequest getRequest = new GetRequest("application/json", success, failure, requestBody.getAuthenticationToken());
        try{
            URL url = new URL("http://10.0.2.2:8080/person/"+requestBody.getPersonId());
            getRequest.execute(url);
        }catch(Exception e){

        }
    }
}
