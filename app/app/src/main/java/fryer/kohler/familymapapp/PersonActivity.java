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
import java.util.function.Supplier;

import familymapapp.Modal.DetailsRowDataObject;
import familymapapp.Modal.Event;
import familymapapp.Modal.Person;
import familymapapp.Modal.TemporaryPersonData;
import familymapapp.Response.EventsResponse;
import familymapapp.Service.EventsService;
import familymapapp.Service.PersonService;
import familymapapp.UTIL.Settings;
import familymapapp.UTIL.Quadruplet;
import familymapapp.UTIL.Util;

public class PersonActivity extends AppCompatActivity {

    Person person;
    RecyclerView detailsRowRecyclerView;
    DetailsRowAdapter detailsRowAdapter;
    TemporaryPersonData personData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        String personId = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        personData = TemporaryPersonData.getInstance();
        detailsRowRecyclerView = (RecyclerView) findViewById(R.id.details_row_events);
        detailsRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        detailsRowAdapter = new DetailsRowAdapter(this);
        loadPerson(personId);

        this.loadPerson(personId);
    }

    public void loadPerson(String personId){
        Consumer<String> success = (data) -> {
            Log.d("debug", data);
            person = (Person) Util.convertJsonStringToObject(data, Person.class);

            Log.d("debug", "person id" + person.getId());
            ArrayList<DetailsRowDataObject> personEvents = personData.getPersonEventDetailsRowObjects(person.getId());

            detailsRowAdapter.setRowContent(personEvents, onClickCallBack);
            detailsRowRecyclerView.setAdapter(detailsRowAdapter);

        };

        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };
        PersonService.get(personId, success, failure);
    }

    private Consumer<String> onClickCallBack = (id) -> {
        Log.d("debug", "YYYYYEESIRRRR" + id);
    };

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


//    public static ArrayList<Quadruplet<String, String, Integer, Runnable>> convertEventsToQuadruplets (ArrayList<Event> events){
//        ArrayList<Quadruplet<String, String, Integer, Runnable>> quadruplets = new ArrayList<Quadruplet<String, String, Integer, Runnable>>();
//        for(Event event: events) {
//
//            quadruplets.add(new Quadruplet<String, String, Integer, Runnable>(event.getEventType(), event.getCity(), R.drawable.common_google_signin_btn_text_dark_focused, onClickCallBack));
//        }
//        return quadruplets;
//    }

}
