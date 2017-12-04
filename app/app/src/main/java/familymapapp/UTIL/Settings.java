package familymapapp.UTIL;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by kittykatt on 12/3/17.
 */

public class Settings {

    private static Settings settings = null;

    private String authenticationToken;
    private String portNumber;
    //todo use global portnumber
    protected Settings() {
        // Exists only to defeat instantiation.
    }

    public static Settings getInstance() {
        if(settings == null) {
            settings = new Settings();
        }
        return settings;
    }

    public String getAuthenticationToken() {
        return authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
        Log.d("debug", "setting auth token to " + authenticationToken);
    }

    public String getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }

}
