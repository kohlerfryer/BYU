package familymapapp.Service;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URL;
import java.util.function.Consumer;

import familymapapp.HTTP.GetRequest;
import familymapapp.Request.PersonRequestBody;
import familymapapp.UTIL.Util;

/**
 * Created by kittykatt on 11/29/17.
 */

public class EventsService {
    //TODO -- make response objects
    //todo--- make authentication token global
    public static void get(String authenticationToken, Consumer<String> success, Consumer<String> failure){

        GetRequest getRequest = new GetRequest("application/json", success, failure, authenticationToken);
        try{
            URL url = new URL("http://10.0.2.2:8000/event");
            getRequest.execute(url);
        }catch(Exception e){

        }
    }
}