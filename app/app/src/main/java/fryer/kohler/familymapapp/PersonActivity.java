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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

import familymapapp.Modal.DataTree;
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

import static fryer.kohler.familymapapp.MainActivity.EXTRA_MESSAGE;

public class PersonActivity extends AppCompatActivity {

    Person person;
    RecyclerView eventDetailsRowRecyclerView;
    RecyclerView personDetailsRowRecyclerView;
    DetailsRowAdapter eventDetailsRowAdapter;
    DetailsRowAdapter personDetailsRowAdapter;
    DataTree dataTree;
    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView genderTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        String personId = intent.getStringExtra(EXTRA_MESSAGE);

        firstNameTextView = (TextView) findViewById(R.id.first_name_text);
        lastNameTextView = (TextView) findViewById(R.id.last_name_text);
        genderTextView = (TextView) findViewById(R.id.gender_text);

        dataTree = DataTree.getInstance();

        eventDetailsRowRecyclerView = (RecyclerView) findViewById(R.id.details_row_events);
        eventDetailsRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personDetailsRowRecyclerView = (RecyclerView) findViewById(R.id.details_row_family);
        personDetailsRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventDetailsRowAdapter = new DetailsRowAdapter(this);
        personDetailsRowAdapter = new DetailsRowAdapter(this);

        this.loadPerson(personId);
    }

    private void loadPerson(String personId){
        Consumer<String> success = (data) -> {
            person = (Person) Util.convertJsonStringToObject(data, Person.class);
            firstNameTextView.setText(person.getFirstName());
            lastNameTextView.setText(person.getLastName());
            genderTextView.setText(person.getGender());

            ArrayList<DetailsRowDataObject> personEvents = Util.castArrayList(
                    dataTree.getFilteredPersonEvents(person.getId(), DataTree.activeEventTypes)
            );

            ArrayList<DetailsRowDataObject> family = Util.castArrayList(
                    dataTree.getFamily(person.getId(), DataTree.activeEventTypes)
            );

            eventDetailsRowAdapter.setRowContent(personEvents, eventOnClickCallBack);
            eventDetailsRowRecyclerView.setAdapter(eventDetailsRowAdapter);

            personDetailsRowAdapter.setRowContent(family, personOnClickCallBack);
            personDetailsRowRecyclerView.setAdapter(personDetailsRowAdapter);

        };

        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };
        PersonService.get(personId, success, failure);
    }

    private Consumer<String> personOnClickCallBack = (personId) -> {
        //Log.d("debug", "YYYYYEESIRRRR" + id);
        Intent intent = new Intent(this, PersonActivity.class);
        Log.d("debug", personId);
        intent.putExtra(EXTRA_MESSAGE, personId);
        startActivity(intent);
    };

    private Consumer<String> eventOnClickCallBack = (eventIid) -> {
        //Log.d("debug", "YYYYYEESIRRRR" + id);
    };


}
