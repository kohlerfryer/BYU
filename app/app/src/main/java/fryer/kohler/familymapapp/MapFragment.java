package fryer.kohler.familymapapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.v4.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.Event;
import familymapapp.Modal.TemporaryPersonData;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 *
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    TextView eventTitleTextView;
    TextView eventBodyTextView;
    ImageView genderImage;
    LinearLayout eventDetailsLayout;
    Event eventInScope;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        eventTitleTextView = (TextView) view.findViewById(R.id.event_title_text);
        eventBodyTextView = (TextView) view.findViewById(R.id.event_body_text);
        genderImage = (ImageView) view.findViewById(R.id.gender_image);
        eventDetailsLayout = (LinearLayout) view.findViewById(R.id.event_details_box);

        eventDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eventInScope != null){
                    MapFragmentHandler mapFragmentHandler = (MapFragmentHandler) getActivity();
                    mapFragmentHandler.handleEventDetailsClick(eventInScope);
                }
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Event event = (Event) marker.getTag();
        this.eventInScope = event;
        eventTitleTextView.setText(event.getPersonId());
        eventBodyTextView.setText(event.getEventType() + ":" + event.getCity() + "," + event.getCountry() + "(" + event.getYear() + ")");

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        googleMap.setOnMarkerClickListener(this);
        DataTree dataTree = DataTree.getInstance();
        for(Event event : dataTree.getFilteredEvents(dataTree.activeEventTypes)){
            Double longitude = Double.parseDouble(event.getLongitude());
            Double lattitude = Double.parseDouble(event.getLatitude());
            LatLng lattitudeLongitude = new LatLng(lattitude, longitude);
            String title = event.getEventType();
            //.icon(BitmapDescriptorFactory.defaultMarker(color)));
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(lattitudeLongitude)
                    .title(title));
            marker.setTag(event);
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(lattitudeLongitude));
        }

    }

    public interface MapFragmentHandler{
        public void handleEventDetailsClick(Event event);
    }

}
