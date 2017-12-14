package fryer.kohler.familymapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import familymapapp.Modal.Person;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class FilterActivity extends AppCompatActivity {

    RecyclerView filterRowRecyclerView;
    FilterRowAdapter filterRowAdapter;

    private Button goToTopButton;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        this.context = this;

        filterRowRecyclerView = (RecyclerView) findViewById(R.id.filter_recycler_view);
        filterRowRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        filterRowAdapter = new FilterRowAdapter(this);
        filterRowRecyclerView.setAdapter(filterRowAdapter);

        goToTopButton = (Button) findViewById(R.id.go_to_top_button);

        goToTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
