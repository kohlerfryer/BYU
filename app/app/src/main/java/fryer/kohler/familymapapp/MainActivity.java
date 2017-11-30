package fryer.kohler.familymapapp;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.widget.Toast;

import java.util.function.Consumer;

import familymapapp.Modal.TemporaryPersonData;
import familymapapp.Response.EventsResponse;
import familymapapp.Service.EventsService;
import familymapapp.UTIL.Util;

import static fryer.kohler.familymapapp.R.*;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentHandler {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //if token expire then
        LoginFragment fragment = new LoginFragment();
        fragmentTransaction.add(id.main_layout, fragment);
        fragmentTransaction.commit();
        //do the stuff above :)

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //TODO USE RSPONSE OBJECT
    @Override
    public void handleLoginSuccess(String personId, String authenticationToken) {

        Consumer<String> success = (data) -> {
            EventsResponse response = (EventsResponse) Util.convertJsonStringToObject(data, EventsResponse.class);
            TemporaryPersonData.getInstance().setEvents(response.getEvents());
            switchFragment(new MapFragment());
        };

        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };
        
        EventsService.get(authenticationToken, success, failure);
    }

    public void switchFragment(Fragment fragment){
        //tODO dispose of code dupllication
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(id.main_layout, fragment);
        fragmentTransaction.commit();
    }
}
