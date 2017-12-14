package fryer.kohler.familymapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.DetailsRowDataObject;
import familymapapp.Modal.Person;
import familymapapp.Util.Util;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
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

    private Button upButton;
    private Button goToTopButton;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        String personId = intent.getStringExtra(EXTRA_MESSAGE);
        context = this;

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

        upButton = (Button) findViewById(R.id.up_button);
        goToTopButton = (Button) findViewById(R.id.go_to_top_button);

        upButton = (Button) findViewById(R.id.up_button);
        goToTopButton = (Button) findViewById(R.id.go_to_top_button);

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        goToTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        goToTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        this.loadPerson(personId);
    }

    private void loadPerson(String personId){
        Log.d("debug", personId);
        Person person = dataTree.getPersons().get(personId);
        firstNameTextView.setText(person.getFirstName());
        lastNameTextView.setText(person.getLastName());
        genderTextView.setText(person.getGender());

        ArrayList<DetailsRowDataObject> personEvents = Util.castArrayList(
                person.getEvents()
        );

        ArrayList<DetailsRowDataObject> family = Util.castArrayList(
                person.getFamily()
        );

        eventDetailsRowAdapter.setRowContent(personEvents, eventOnClickCallBack);
        eventDetailsRowRecyclerView.setAdapter(eventDetailsRowAdapter);

        personDetailsRowAdapter.setRowContent(family, personOnClickCallBack);
        personDetailsRowRecyclerView.setAdapter(personDetailsRowAdapter);

    }

    private Consumer<String> personOnClickCallBack = (personId) -> {
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra(EXTRA_MESSAGE, personId);
        startActivity(intent);
    };

    private Consumer<String> eventOnClickCallBack = (eventId) -> {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(EXTRA_MESSAGE, eventId);
        startActivity(intent);
    };


}
