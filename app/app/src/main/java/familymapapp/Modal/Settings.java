package familymapapp.Modal;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by programmer on 12/10/17.
 */

public class Settings {

    private static Settings settings = null;

    private HashMap<String, Integer> lifeStoryLineColors;
    private HashMap<String, Integer> familyTreeLineColors;
    private HashMap<String, Integer> spouseLineColors;

    public String selectedLifeStoryColor = "green";
    public String selectedFamilyTreeColor = "blue";
    public String selectedSpouseColor = "red";

    private boolean lifeStoryLinesActive = true;
    private boolean familyTreeLinesActive = true;
    private boolean spouseLinesActive = true;

    protected Settings() {
        lifeStoryLineColors = new HashMap<>();
        familyTreeLineColors = new HashMap<>();
        spouseLineColors = new HashMap<>();

        this.selectedLifeStoryColor = "green";
        this.selectedFamilyTreeColor = "blue";
        this.selectedSpouseColor = "red";

        lifeStoryLineColors.put(this.selectedLifeStoryColor, Color.GREEN);
        familyTreeLineColors.put(this.selectedFamilyTreeColor, Color.BLUE);
        spouseLineColors.put(this.selectedSpouseColor, Color.RED);

        lifeStoryLineColors.put("black", Color.BLACK);
        familyTreeLineColors.put("cyan", Color.CYAN);
        spouseLineColors.put("grey", Color.DKGRAY);
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



    public int getSelectedLifeStoryLineColor(){
        return lifeStoryLineColors.get(this.selectedLifeStoryColor);
    }
    public int getSelectedFamilyTreeLineColor(){
        return familyTreeLineColors.get(this.selectedFamilyTreeColor);
    }
    public int getSelectedSpouseLineColor(){
        return spouseLineColors.get(this.selectedSpouseColor);
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

    private ArrayList<String> getKeys(HashMap<String, Integer> map){
        ArrayList<String> colors = new ArrayList<String>();
        for (HashMap.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer color = entry.getValue();
            colors.add(key);
        }
        return colors;
    }



}
