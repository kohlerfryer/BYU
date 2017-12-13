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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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

    public void setEventTextViewData(Event event){
        this.eventInScope = event;
        eventTitleTextView.setText(event.getPersonId());
        eventBodyTextView.setText(event.getEventType() + ":" + event.getCity() + "," + event.getCountry() + "(" + event.getYear() + ")");
        polylineDrawer.drawLines(event);
        eventInFocus = event;
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        Event event = (Event) marker.getTag();
        setEventTextViewData(event);
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
        }
        if(eventInFocus != null){
            polylineDrawer.drawLines(eventInFocus);
        }

    }

    public void setUpMap(){
        polylineDrawer = new PolylineDrawer(googleMap);
        googleMap.setOnMarkerClickListener(this);
        googleMap.setMapType(Settings.getInstance().getMapType());
        DataTree dataTree = DataTree.getInstance();
        for(Event event : dataTree.getFilteredEvents()){
            Double longitude = Double.parseDouble(event.getLongitude());
            Double lattitude = Double.parseDouble(event.getLatitude());
            LatLng lattitudeLongitude = new LatLng(lattitude, longitude);
            String title = event.getEventType();
            //.icon(BitmapDescriptorFactory.defaultMarker(color)));
            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(lattitudeLongitude)
                    .title(title)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
            marker.setTag(event);
        }
        if(getArguments() != null && getArguments() .getString("eventIdInFocus") != null){
            eventInFocus = DataTree.getEvents().get(getArguments().getString("eventIdInFocus"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(eventInFocus.convertCoordinatesToLtLng()));
            polylineDrawer.drawLines(eventInFocus);
            setEventTextViewData(eventInFocus);
        }

    }


    public interface MapFragmentHandler{
        public void handleEventDetailsClick(Event event);
    }

}
