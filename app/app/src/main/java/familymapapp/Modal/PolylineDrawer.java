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

/**
 * Created by programmer on 12/11/17.
 */

public class PolylineDrawer {
    ArrayList<Polyline> spouseLines;
    ArrayList<Polyline> familyLines;
    GoogleMap googleMap;

    public PolylineDrawer(GoogleMap googleMap){
        spouseLines = new ArrayList<>();
        this.googleMap = googleMap;
    }

    public void clearPolyLines(){
        for(Polyline polyline : spouseLines){
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
        drawSpouseLines(event);
        drawFamilyTreeLines(event);
    }

    private void drawSpouseLines(Event event){
        Person person = DataTree.getInstance().getPerson(event.getPersonId());
        if(person.getSpouseId() != null){
            Event spouseEvent = DataTree.getInstance().getPersonsEarliestEvent(person.getSpouseId());
            createPolyline(spouseLines, event.convertCoordinatesToLtLng(), spouseEvent.convertCoordinatesToLtLng(), Color.RED, 10);
        }
    }

    private void drawFamilyTreeLines(Event event){
        Person person = DataTree.getInstance().getPerson(event.getPersonId());
        if(person.getFatherId() != null){
            Event fatherEvent = DataTree.getInstance().getPersonsEarliestEvent(person.getFatherId());
            createPolyline(familyLines, event.convertCoordinatesToLtLng(), fatherEvent.convertCoordinatesToLtLng(), Color.BLUE, 10);
            drawFamilyTreeLines(fatherEvent);
        }
        if(person.getMotherId() != null){
            Event motherEvent = DataTree.getInstance().getPersonsEarliestEvent(person.getMotherId());
            createPolyline(familyLines, event.convertCoordinatesToLtLng(), motherEvent.convertCoordinatesToLtLng(), Color.BLUE, 10);
            drawFamilyTreeLines(motherEvent);
        }
    }
}
