package familymapapp.Service;

import java.net.URL;
import java.util.function.Consumer;

import familymapapp.Proxy.GetRequest;
import familymapapp.Proxy.Proxy;
import familymapapp.Util.Settings;

/**
 * Created by kittykatt on 11/29/17.
 */

public class EventsService {
    public static void get(Consumer<String> success, Consumer<String> failure){
        Settings settings = Settings.getInstance();
        GetRequest getRequest = new GetRequest("application/json", success, failure, Proxy.authenticationToken);
        try{
            URL url = new URL("http://10.0.2.2:8000/event");
            getRequest.execute(url);
        }catch(Exception e){

        }
    }
}
