package familymapapp.Modal;

import android.graphics.Color;

import com.google.android.gms.maps.GoogleMap;

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

    private HashMap<String, Integer> mapTypes;

    public String selectedLifeStoryColor = "green";
    public String selectedFamilyTreeColor = "blue";
    public String selectedSpouseColor = "red";

    public String selectedMapType = "normal";

    public boolean lifeStoryLinesActive = true;
    public boolean familyTreeLinesActive = true;
    public boolean spouseLinesActive = true;

    protected void initialize() {
        lifeStoryLineColors = new HashMap<>();
        familyTreeLineColors = new HashMap<>();
        spouseLineColors = new HashMap<>();
        mapTypes = new HashMap<>();

        lifeStoryLineColors.put(selectedLifeStoryColor, Color.GREEN);
        familyTreeLineColors.put(selectedFamilyTreeColor, Color.BLUE);
        spouseLineColors.put(selectedSpouseColor, Color.RED);

        mapTypes.put("normal", GoogleMap.MAP_TYPE_NORMAL);
        mapTypes.put("hybrid", GoogleMap.MAP_TYPE_HYBRID);
        mapTypes.put("satellite", GoogleMap.MAP_TYPE_SATELLITE);
        mapTypes.put("terrain", GoogleMap.MAP_TYPE_TERRAIN);

        lifeStoryLineColors.put("black", Color.BLACK);
        familyTreeLineColors.put("cyan", Color.CYAN);
        spouseLineColors.put("grey", Color.DKGRAY);
    }

    public static Settings getInstance() {
        if (settings == null) {
            settings = new Settings();
            settings.initialize();
        }
        return settings;
    }

    public int getSelectedLifeStoryLineColor(){
        return lifeStoryLineColors.get(selectedLifeStoryColor);
    }
    public int getSelectedFamilyTreeLineColor(){
        return familyTreeLineColors.get(selectedFamilyTreeColor);
    }
    public int getSelectedSpouseLineColor(){
        return spouseLineColors.get(selectedSpouseColor);
    }
    public int getMapType(){
        return mapTypes.get(selectedMapType);
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
    public ArrayList<String> getMapTypeKeys(){
        return getKeys(this.mapTypes);
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



    public void setSelectedLifeStoryLineColor(String color){
        this.selectedLifeStoryColor = color;
    }
    public void setSelectedFamilyTreeLineColor(String color){
        this.selectedFamilyTreeColor = color;
    }
    public void setSelectedSpouseLineColor(String color){
        this.selectedSpouseColor = color;
    }
    public void setSelectedMapType(String type){
        this.selectedMapType = type;
    }
    public void setFasmilyTreeLinesActive(boolean b){
        familyTreeLinesActive = b;
    }
    public void setLifeStoryLinesActive(boolean b){
        lifeStoryLinesActive = b;
    }
    public void setSpouseLinesActive(boolean b){
        spouseLinesActive = b;

    }



}
