package fryer.kohler.familymapapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.Event;
import familymapapp.Modal.Person;
import familymapapp.Modal.TemporaryPersonData;
import familymapapp.Response.EventsResponse;
import familymapapp.Response.PersonsResponse;
import familymapapp.Service.EventsService;
import familymapapp.Service.PersonService;
import familymapapp.Service.PersonsService;
import familymapapp.UTIL.Settings;
import familymapapp.UTIL.Util;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static fryer.kohler.familymapapp.R.*;

public class MainActivity extends AppCompatActivity implements LoginFragment.LoginFragmentHandler, MapFragment.MapFragmentHandler {

    public static final String EXTRA_MESSAGE = "fryer.kohler.familymapapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //if token expire then
        LoginFragment fragment = new LoginFragment();
        fragmentTransaction.replace(id.main_layout, fragment);
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

        Settings settings = Settings.getInstance();
        settings.setAuthenticationToken(authenticationToken);


        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };

        Consumer<String> eventSuccess = (data) -> {
            EventsResponse response = (EventsResponse) Util.convertJsonStringToObject(data, EventsResponse.class);
            DataTree.getInstance().setEvents(response.getEvents());
            switchFragment(new MapFragment());
        };

        Consumer<String> personsSuccess = (data) -> {
            PersonsResponse response = (PersonsResponse) Util.convertJsonStringToObject(data, PersonsResponse.class);
            DataTree.getInstance().setPersons(response.getPersons());
            EventsService.get(eventSuccess, failure);
        };

        Consumer<String> personSuccess = (data) -> {
            Person person = (Person) Util.convertJsonStringToObject(data, Person.class);
            DataTree.getInstance().setRootPerson(person);
            PersonsService.get(personsSuccess, failure);
        };

        PersonService.get(personId, personSuccess, failure);

    }

    public void switchFragment(Fragment fragment){
        //tODO dispose of code dupllication
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(id.main_layout, fragment);
        fragmentTransaction.commit();
    }

    public void handleSettingsClick(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void handleFilterClick(){
        Intent intent = new Intent(this, FilterActivity.class);
        startActivity(intent);
    }


    @Override
    public void handleEventDetailsClick(Event event) {
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra(EXTRA_MESSAGE, event.getPersonId());
        startActivity(intent);
    }
}
