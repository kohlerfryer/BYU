package familymapapp.Modal;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by programmer on 12/10/17.
 */

public class Settings {

    private static Settings settings = null;

    private HashMap<String, String> lifeStoryLineColors;
    private HashMap<String, String> familyTreeLineColors;
    private HashMap<String, String> spouseLineColors;

    private String selectedLifeStoryColor = "green";
    private String selectedFamilyTreeColor = "blue";
    private String selectedSpouseColor = "red";

    private boolean lifeStoryLinesActive = true;
    private boolean familyTreeLinesActive = true;
    private boolean spouseLinesActive = true;

    protected Settings() {
        lifeStoryLineColors = new HashMap<>();
        familyTreeLineColors = new HashMap<>();
        spouseLineColors = new HashMap<>();

        lifeStoryLineColors.put(selectedLifeStoryColor, "#4CAF50");
        familyTreeLineColors.put(selectedFamilyTreeColor, "#80D8FF");
        spouseLineColors.put(selectedSpouseColor, "#DD2C00");
    }

    public static Settings getInstance() {
        if (settings == null) {
            settings = new Settings();
        }
        return settings;
    }



    public void setSelectedLifeStoryLineColor(String color){
        this.selectedLifeStoryColor = color;
    }
    public void setSelectedFamilyTreeLineColor(String color){
        this.selectedFamilyTreeColor = color;
    }
    public void setSelectedSpouseLineColor(String color){
        this.selectedLifeStoryColor = color;
    }
    public void setFamilyTreeLinesActive(boolean b){
        familyTreeLinesActive = b;
    }
    public void setLifeStoryLinesActive(boolean b){
        lifeStoryLinesActive = b;
    }
    public void setSpouseLinesActive(boolean b){
        spouseLinesActive = b;
    }



    public String getSelectedLifeStoryLineColor(){
        return this.selectedLifeStoryColor;
    }
    public String getSelectedFamilyTreeLineColor(){
        return this.selectedFamilyTreeColor;
    }
    public String getSelectedSpouseLineColor(){
        return this.selectedLifeStoryColor;
    }
    public boolean getLifeStoryLinesActive(){
        return this.lifeStoryLinesActive;
    }
    public boolean getFamilyTreeLinesActive(){
        return this.familyTreeLinesActive;
    }
    public boolean getSpouseLinesActive(){
        return this.spouseLinesActive;
    }


    public ArrayList<String> getLifeStoryLineColorKeys(){
        return getKeys(this.lifeStoryLineColors);
    }

    public ArrayList<String> getFamilyTreeColorKeys(){
        return getKeys(this.familyTreeLineColors);
    }

    public ArrayList<String> getSpouseLineColorKeys(){
        return getKeys(this.spouseLineColors);
    }

    private ArrayList<String> getKeys(HashMap<String, String> map){
        ArrayList<String> colors = new ArrayList<String>();
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String color = entry.getValue();
            colors.add(key);
        }
        return colors;
    }



}
