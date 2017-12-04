package fryer.kohler.familymapapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.function.Consumer;

import familymapapp.Modal.Person;
import familymapapp.Service.PersonService;
import familymapapp.UTIL.Util;

public class PersonActivity extends AppCompatActivity {

    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        String personId = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d("debug", personId);
        this.loadPerson(personId);
    }

    public void loadPerson(String personId){
        Consumer<String> success = (data) -> {
            Log.d("debug", data);
            person = (Person) Util.convertJsonStringToObject(data, Person.class);
            Log.d("debug", person.getGender());

        };

        Consumer<String> failure = (data) -> {
            Toast.makeText(this, Util.getValueFromJson(data, "message"), 30000).show();
        };
        PersonService.get(personId, success, failure);
    }

}
