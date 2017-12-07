package fryer.kohler.familymapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.function.Consumer;

import familymapapp.Modal.Event;
import familymapapp.Modal.Person;
import familymapapp.Modal.TemporaryPersonData;
import familymapapp.Response.EventsResponse;
import familymapapp.Service.EventsService;
import familymapapp.Service.PersonService;
import familymapapp.UTIL.Settings;
import familymapapp.UTIL.Triplet;
import familymapapp.UTIL.Util;

public class PersonActivity extends AppCompatActivity {

    Person person;
    RecyclerView detailsRowRecyclerView;
    DetailsRowAdapter detailsRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        String personId = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        detailsRowRecyclerView = (RecyclerView) findViewById(R.id.details_row);
        detailsRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsRowAdapter = new DetailsRowAdapter(this);
        detailsRowAdapter.setRowContent();
        detailsRowRecyclerView.setAdapter(detailsRowAdapter);
        Log.d("debug", personId);
        this.loadPerson(personId);
    }

    public void loadPerson(String personId){
        Consumer<String> success = (data) -> {
            Log.d("debug", data);
            person = (Person) Util.convertJsonStringToObject(data, Person.class);
            //ArrayList<Triplet<String, String, Integer>> data = convertEventsToTriplets()
            Log.d("debug", person.getGender());

        };

        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };
        PersonService.get(personId, success, failure);
    }

//    public void getPersonEvents(String personId){
//
//        Consumer<String> success = (data) -> {
//            EventsResponse response = (EventsResponse) Util.convertJsonStringToObject(data, EventsResponse.class);
//            Event[] events = response.getEvents();
//
//            detailsRowAdapter.setRowContent();
//            detailsRowRecyclerView.setAdapter(detailsRowAdapter);
//        };
//
//        Consumer<String> failure = (data) -> {
//            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
//        };
//
//        EventsService.get(success, failure);
//    }


    public static ArrayList<Triplet<String, String, Integer>> convertEventsToTriplets (Event[] events){
        ArrayList<Triplet<String, String, Integer>> triplets = new ArrayList<Triplet<String, String, Integer>>();
        for(Event event: events) {
            triplets.add(new Triplet<String, String, Integer>(event.getEventType(), event.getCity(), R.drawable.common_google_signin_btn_text_dark_focused));
        }
        return triplets;
    }

}
