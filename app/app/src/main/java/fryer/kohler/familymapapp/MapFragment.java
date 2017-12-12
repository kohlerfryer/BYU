package fryer.kohler.familymapapp;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import familymapapp.Modal.DataTree;
import familymapapp.Modal.Event;
import familymapapp.Modal.PolylineDrawer;
import familymapapp.Modal.Settings;
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
    Button filterButton;
    Button settingsButton;
    ImageView genderImage;
    LinearLayout eventDetailsLayout;
    Event eventInScope;
    GoogleMap googleMap;
    PolylineDrawer polylineDrawer;
    Event eventInFocus;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        eventTitleTextView = (TextView) view.findViewById(R.id.event_title_text);
        eventBodyTextView = (TextView) view.findViewById(R.id.event_body_text);
        genderImage = (ImageView) view.findViewById(R.id.gender_image);
        eventDetailsLayout = (LinearLayout) view.findViewById(R.id.event_details_box);
        filterButton = (Button) view.findViewById(R.id.filter_button);
        settingsButton = (Button) view.findViewById(R.id.settings_button);

        eventDetailsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(eventInScope != null){
                    MapFragmentHandler mapFragmentHandler = (MapFragmentHandler) getActivity();
                    mapFragmentHandler.handleEventDetailsClick(eventInScope);
                }
            }
        });

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragmentHandler mapFragmentHandler = (MapFragmentHandler) getActivity();
                mapFragmentHandler.handleFilterClick();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapFragmentHandler mapFragmentHandler = (MapFragmentHandler) getActivity();
                mapFragmentHandler.handleSettingsClick();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Event event = (Event) marker.getTag();
        this.eventInScope = event;
        eventTitleTextView.setText(event.getPersonId());
        eventBodyTextView.setText(event.getEventType() + ":" + event.getCity() + "," + event.getCountry() + "(" + event.getYear() + ")");
        polylineDrawer.drawLines(event);
        eventInFocus = event;
        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        setUpMap();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(googleMap != null){
            googleMap.clear();
            setUpMap();
            polylineDrawer.drawLines(eventInFocus);
        }

    }

    public void setUpMap(){
        polylineDrawer = new PolylineDrawer(googleMap);
        googleMap.setOnMarkerClickListener(this);
        DataTree dataTree = DataTree.getInstance();
        for(Event event : dataTree.getFilteredEvents()){
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
        public void handleFilterClick();
        public void handleSettingsClick();
    }

}
