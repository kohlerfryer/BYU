package fryer.kohler.familymapapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import familymapapp.Modal.Person;

public class FilterActivity extends AppCompatActivity {

    Person person;
    RecyclerView filterRowRecyclerView;
    FilterRowAdapter filterRowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        filterRowRecyclerView = (RecyclerView) findViewById(R.id.filter_recycler_view);
        filterRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        filterRowAdapter = new FilterRowAdapter(this);
        filterRowRecyclerView.setAdapter(filterRowAdapter);

    }
}
