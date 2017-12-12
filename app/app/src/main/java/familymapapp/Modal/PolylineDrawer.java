package familymapapp.Modal;

import android.graphics.Color;
import android.provider.ContactsContract;
import android.system.StructPollfd;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by programmer on 12/11/17.
 */

public class PolylineDrawer {
    ArrayList<Polyline> spouseLines;
    ArrayList<Polyline> familyLines;
    ArrayList<Polyline> lifeEventLines;
    GoogleMap googleMap;

    public PolylineDrawer(GoogleMap googleMap){
        spouseLines = new ArrayList<>();
        familyLines = new ArrayList<>();
        lifeEventLines = new ArrayList<>();
        this.googleMap = googleMap;
    }

    public void clearPolyLines(){
        for(Polyline polyline : spouseLines){
            polyline.remove();
        }
        for(Polyline polyline : familyLines){
            polyline.remove();
        }
        for(Polyline polyline : lifeEventLines){
            polyline.remove();
        }
    }

    public void createPolyline(ArrayList<Polyline> polylines, LatLng point1, LatLng point2, int color, int width){
        PolylineOptions polylineOptions =  new PolylineOptions();
        polylineOptions.add(point1, point2).color(color).width(width);
        polylines.add(googleMap.addPolyline(polylineOptions));
    }

    public void drawLines(Event event){
        clearPolyLines();
        drawSpouseLines(event, 10);
        drawFamilyTreeLines(event, 10);
        drawLifeEvents(event, 10);
    }

    private void drawSpouseLines(Event event, int width){
        Person person = DataTree.getInstance().getPersons().get(event.getPersonId());
        if(person.getSpouse() != null){
            Event spouseEvent = person.getSpouse().getEvents().get(0);
            createPolyline(spouseLines, event.convertCoordinatesToLtLng(), spouseEvent.convertCoordinatesToLtLng(), Settings.getInstance().getSelectedSpouseLineColor(), width);
        }
    }

    private void drawFamilyTreeLines(Event event, int width){
        Person person = DataTree.getInstance().getPersons().get(event.getPersonId());
        if(person.getFather() != null){
            Event fatherEvent = person.getFather().getEvents().get(0);
            createPolyline(familyLines, event.convertCoordinatesToLtLng(), fatherEvent.convertCoordinatesToLtLng(), Settings.getInstance().getSelectedFamilyTreeLineColor(), width);
            drawFamilyTreeLines(fatherEvent, width-2);
        }
        if(person.getMother() != null){
            Event motherEvent = person.getMother().getEvents().get(0);
            createPolyline(familyLines, event.convertCoordinatesToLtLng(), motherEvent.convertCoordinatesToLtLng(), Settings.getInstance().getSelectedFamilyTreeLineColor(), width);
            drawFamilyTreeLines(motherEvent, width-2);
        }
    }

    private void drawLifeEvents(Event event, int width){
        Person person = DataTree.getInstance().getPersons().get(event.getPersonId());
        ArrayList<Event> lifeEvents = person.getEvents();
        for(int i = 0; i < person.getEvents().size() - 1; i++){
            createPolyline(lifeEventLines, lifeEvents.get(i).convertCoordinatesToLtLng(), lifeEvents.get(i+1).convertCoordinatesToLtLng(), Settings.getInstance().getSelectedLifeStoryLineColor(), width);
        }
    }
}
