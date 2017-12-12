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
import familymapapp.UTIL.Settings;
import familymapapp.UTIL.Util;

public class PersonsService {

    //TODO -- make response objects
    public static void get(Consumer<String> success, Consumer<String> failure){
        JsonObject person;
        //todo make model for person -- -make all service classes unison and clean :_
        Settings settings = Settings.getInstance();
        GetRequest getRequest = new GetRequest("application/json", success, failure, settings.getAuthenticationToken());
        try{
            URL url = new URL("http://10.0.2.2:8000/person");
            getRequest.execute(url);
        }catch(Exception e){

        }
    }
}
