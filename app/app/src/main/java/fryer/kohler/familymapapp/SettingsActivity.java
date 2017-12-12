package fryer.kohler.familymapapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import familymapapp.Modal.Settings;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        lifeStoryLinesSpinner = (Spinner) findViewById(R.id.life_story_lines_spinner);
        familyTreeLinesSpinner = (Spinner) findViewById(R.id.family_tree_spinner);
        spouseLinesSpinner = (Spinner) findViewById(R.id.spouse_spinner);
        mapTypeSpinner = (Spinner) findViewById(R.id.map_type_spinner);

        lifeStoryLinesSwitch = (Switch) findViewById(R.id.life_story_lines_switch);
        familyTreeLinesSwitch = (Switch) findViewById(R.id.family_tree_lines_switch);
        spouseLinesSwitch = (Switch) findViewById(R.id.spouse_lines_switch);

        ArrayAdapter<String> LifeStoryArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getLifeStoryLineColorKeys()
        );
        ArrayAdapter<String> familyTreeArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getFamilyTreeColorKeys()
        );
        ArrayAdapter<String> spouseArrayAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, Settings.getInstance().getSpouseLineColorKeys()
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

        lifeStoryLinesSpinner.setAdapter(LifeStoryArrayAdapter);
        familyTreeLinesSpinner.setAdapter(familyTreeArrayAdapter);
        spouseLinesSpinner.setAdapter(spouseArrayAdapter);

        setSpinnerValue(LifeStoryArrayAdapter, lifeStoryLinesSpinner, Settings.getInstance().selectedLifeStoryColor);
        setSpinnerValue(familyTreeArrayAdapter, familyTreeLinesSpinner, Settings.getInstance().selectedFamilyTreeColor);
        setSpinnerValue(spouseArrayAdapter, spouseLinesSpinner, Settings.getInstance().selectedSpouseColor);
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


    }

    private void setSpinnerValue(ArrayAdapter adapter, Spinner spinner,  String value){
        int spinnerPosition = adapter.getPosition(value);
        spinner.setSelection(spinnerPosition);
    }

}
