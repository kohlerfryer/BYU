package fryer.kohler.familymapapp;

import android.widget.Toast;

import org.junit.Test;

import java.util.function.Consumer;

import familymapapp.Request.LoginRequestBody;
import familymapapp.Request.RegisterRequestBody;
import familymapapp.Service.LoginService;
import familymapapp.UTIL.Settings;
import familymapapp.UTIL.Util;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by kittykatt on 12/11/17.
 */

public class DataTreeUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        LoginRequestBody requestBody = new LoginRequestBody(
                "aa",
                "password"
        );

        Consumer<String> success = (data) -> {
            String authenticationToken = Util.getValueFromJson(data, "authToken");
            String personId = Util.getValueFromJson(data, "personID");
            Settings.getInstance().setAuthenticationToken(authenticationToken);

        };

        Consumer<String> failure = (data) -> {
            //throw new RuntimeException();
        };

        LoginService.login("10.0.2.2", "8000", requestBody, success, failure);



        //assertEquals(4, 2 + 2);
    }


}
