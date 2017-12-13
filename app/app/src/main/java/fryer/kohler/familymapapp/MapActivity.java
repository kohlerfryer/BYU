package fryer.kohler.familymapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import familymapapp.Modal.Event;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static fryer.kohler.familymapapp.MainActivity.EXTRA_MESSAGE;

public class MapActivity extends AppCompatActivity implements MapFragment.MapFragmentHandler {

    private Button upButton;
    private Button goToTopButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        context = this;
        String eventIdInFocus = intent.getStringExtra(EXTRA_MESSAGE);
        upButton = (Button) findViewById(R.id.up_button);
        goToTopButton = (Button) findViewById(R.id.go_to_top_button);

        Bundle bundle = new Bundle();
        bundle.putString("eventIdInFocus", eventIdInFocus);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.map, fragment);
        fragmentTransaction.commit();

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
