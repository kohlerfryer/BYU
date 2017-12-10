package fryer.kohler.familymapapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

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


    }

}
