package familymapapp.Service;


import com.google.gson.JsonObject;

import java.net.URL;
import java.util.function.Consumer;

import familymapapp.Proxy.GetRequest;
import familymapapp.Proxy.Proxy;
import familymapapp.Util.Settings;

public class PersonsService {

    public static void get(Consumer<String> success, Consumer<String> failure){
        JsonObject person;
        Settings settings = Settings.getInstance();
        GetRequest getRequest = new GetRequest("application/json", success, failure, Proxy.authenticationToken);
        try{
            URL url = new URL("http://10.0.2.2:8000/person");
            getRequest.execute(url);
        }catch(Exception e){

        }
    }
}
