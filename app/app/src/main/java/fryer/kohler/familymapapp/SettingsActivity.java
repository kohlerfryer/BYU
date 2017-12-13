package fryer.kohler.familymapapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.function.Consumer;

import familymapapp.HTTP.Proxy;
import familymapapp.Modal.Settings;
import familymapapp.UTIL.Util;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

public class SettingsActivity extends AppCompatActivity {

    private Spinner lifeStoryLinesSpinner;
    private Spinner familyTreeLinesSpinner;
    private Spinner spouseLinesSpinner;
    private Spinner mapTypeSpinner;

    private Switch lifeStoryLinesSwitch;
    private Switch familyTreeLinesSwitch;
    private Switch spouseLinesSwitch;

    private TextView resyncDataTextView;
    private TextView logoutTextView;

    private Button upButton;
    private Button goToTopButton;

    private Context context;

    private Consumer<String> rysyncSuccess;

    private Consumer<String> rysncFailure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        context = this;

        lifeStoryLinesSpinner = (Spinner) findViewById(R.id.life_story_lines_spinner);
        familyTreeLinesSpinner = (Spinner) findViewById(R.id.family_tree_spinner);
        spouseLinesSpinner = (Spinner) findViewById(R.id.spouse_spinner);
        mapTypeSpinner = (Spinner) findViewById(R.id.map_type_spinner);

        lifeStoryLinesSwitch = (Switch) findViewById(R.id.life_story_lines_switch);
        familyTreeLinesSwitch = (Switch) findViewById(R.id.family_tree_lines_switch);
        spouseLinesSwitch = (Switch) findViewById(R.id.spouse_lines_switch);

        resyncDataTextView = (TextView) findViewById(R.id.resync_data_text_view);
        logoutTextView = (TextView) findViewById(R.id.logout_text_view);

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

        ArrayAdapter<String> LifeStoryArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getLifeStoryLineColorKeys()
        );
        ArrayAdapter<String> familyTreeArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getFamilyTreeColorKeys()
        );
        ArrayAdapter<String> spouseArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getSpouseLineColorKeys()
        );
        ArrayAdapter<String> mapArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getMapTypeKeys()
        );

        LifeStoryArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        familyTreeArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        spouseArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        mapArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        lifeStoryLinesSpinner.setAdapter(LifeStoryArrayAdapter);
        familyTreeLinesSpinner.setAdapter(familyTreeArrayAdapter);
        spouseLinesSpinner.setAdapter(spouseArrayAdapter);
        mapTypeSpinner.setAdapter(mapArrayAdapter);

        setSpinnerValue(LifeStoryArrayAdapter, lifeStoryLinesSpinner, Settings.getInstance().selectedLifeStoryColor);
        setSpinnerValue(familyTreeArrayAdapter, familyTreeLinesSpinner, Settings.getInstance().selectedFamilyTreeColor);
        setSpinnerValue(spouseArrayAdapter, spouseLinesSpinner, Settings.getInstance().selectedSpouseColor);
        setSpinnerValue(mapArrayAdapter, mapTypeSpinner, Settings.getInstance().selectedMapType);
        lifeStoryLinesSwitch.setChecked(Settings.getInstance().getLifeStoryLinesActive());
        familyTreeLinesSwitch.setChecked(Settings.getInstance().getFamilyTreeLinesActive());
        spouseLinesSwitch.setChecked(Settings.getInstance().getSpouseLinesActive());

        lifeStoryLinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String color = (String) parent.getItemAtPosition(position).toString();
                Settings.getInstance().setSelectedLifeStoryLineColor(color);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        familyTreeLinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String color = (String) parent.getItemAtPosition(position).toString();
                Settings.getInstance().setSelectedFamilyTreeLineColor(color);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spouseLinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String color = (String) parent.getItemAtPosition(position).toString();
                Settings.getInstance().setSelectedSpouseLineColor(color);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        mapTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String type = (String) parent.getItemAtPosition(position).toString();
                Settings.getInstance().setSelectedMapType(type);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        lifeStoryLinesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.getInstance().setLifeStoryLinesActive(isChecked);
            }
        });

        spouseLinesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.getInstance().setSpouseLinesActive(isChecked);
            }
        });

        familyTreeLinesSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Settings.getInstance().setFasmilyTreeLinesActive(isChecked);
            }
        });

        rysyncSuccess = (data) -> {
            Toast.makeText(this, "re-synced", 3000).show();
        };

        rysncFailure = (data) -> {
            Toast.makeText(this, "error re-syncing", 30000).show();
        };

        resyncDataTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proxy.syncData(rysyncSuccess, rysncFailure);
            }
        });

        logoutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Proxy.logout();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setSpinnerValue(ArrayAdapter adapter, Spinner spinner,  String value){
        int spinnerPosition = adapter.getPosition(value);
        spinner.setSelection(spinnerPosition);
    }

}
