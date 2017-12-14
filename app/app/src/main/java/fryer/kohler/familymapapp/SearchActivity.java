package fryer.kohler.familymapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.function.Consumer;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.DetailsRowDataObject;
import familymapapp.Util.Util;

import static fryer.kohler.familymapapp.MainActivity.EXTRA_MESSAGE;

public class SearchActivity extends AppCompatActivity {

    RecyclerView eventDetailsRowRecyclerView;
    RecyclerView personDetailsRowRecyclerView;
    DetailsRowAdapter eventDetailsRowAdapter;
    DetailsRowAdapter personDetailsRowAdapter;
    EditText searchField;
    DataTree dataTree;
    private Button goToTopButton;
    private Context context;

    private Consumer<String> personOnClickCallBack = (personId) -> {
        Log.d("debug", personId);
        Intent intent = new Intent(this, PersonActivity.class);
        intent.putExtra(EXTRA_MESSAGE, personId);
        startActivity(intent);
    };

    private Consumer<String> eventOnClickCallBack = (eventId) -> {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(EXTRA_MESSAGE, eventId);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.context = context;

        dataTree = DataTree.getInstance();

        searchField = (EditText) findViewById(R.id.search_field);

        eventDetailsRowRecyclerView = (RecyclerView) findViewById(R.id.event_results);
        eventDetailsRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        personDetailsRowRecyclerView = (RecyclerView) findViewById(R.id.person_results);
        personDetailsRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        eventDetailsRowAdapter = new DetailsRowAdapter(this);
        personDetailsRowAdapter = new DetailsRowAdapter(this);
        goToTopButton = (Button) findViewById(R.id.go_to_top_button);

        goToTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchField.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(searchField.getText().toString());
            }
        });


    }

    public void search(String text){

        ArrayList<DetailsRowDataObject> personResults = Util.castArrayList(
                dataTree.searchPersons(text)
        );

        ArrayList<DetailsRowDataObject> eventResults = Util.castArrayList(
                dataTree.searchEvents(text)
        );

        eventDetailsRowAdapter.setRowContent(eventResults, eventOnClickCallBack);
        eventDetailsRowRecyclerView.setAdapter(eventDetailsRowAdapter);
        personDetailsRowAdapter.setRowContent(personResults, personOnClickCallBack);
        personDetailsRowRecyclerView.setAdapter(personDetailsRowAdapter);
    }

}
