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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.function.Consumer;

import familymapapp.HTTP.Proxy;
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
    Button filterButton;
    Button settingsButton;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        filterButton = (Button) findViewById(R.id.filter_button);
        settingsButton = (Button) findViewById(R.id.settings_button);
        searchButton = (Button) findViewById(R.id.search_button);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleFilterClick();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSettingsClick();
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSearchClick();
            }
        });

        if(Proxy.authenticationToken == null){
            filterButton.setVisibility(View.GONE);
            settingsButton.setVisibility(View.GONE);
            searchButton.setVisibility(View.GONE);
            LoginFragment fragment = new LoginFragment();
            fragmentTransaction.replace(id.fragment, fragment);
            fragmentTransaction.commit();
        }else{
            MapFragment fragment = new MapFragment();
            fragmentTransaction.replace(id.fragment, fragment);
            fragmentTransaction.commit();
        }

    }

    //TODO USE RSPONSE OBJECT
    @Override
    public void handleLoginSuccess(String personId, String authenticationToken) {
        Proxy.authenticationToken = authenticationToken;
        Proxy.rootPersonId = personId;

        Consumer<String> success = (data) -> {
            filterButton.setVisibility(View.VISIBLE);
            settingsButton.setVisibility(View.VISIBLE);
            searchButton.setVisibility(View.VISIBLE);
            switchFragment(new MapFragment());
        };

        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };

        Proxy.syncData(success, failure);

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

    public void handleSearchClick(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


    @Override
    public void handleEventDetailsClick(Event event) {
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra(EXTRA_MESSAGE, event.getPersonId());
        startActivity(intent);
    }
}
