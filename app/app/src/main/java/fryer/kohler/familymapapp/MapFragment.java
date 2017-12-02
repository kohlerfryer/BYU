package fryer.kohler.familymapapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

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
        Integer clickCount = (Integer) marker.getTag();
        Event event = (Event) marker.getTag();

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        TemporaryPersonData personData = TemporaryPersonData.getInstance();
        for(Event event : personData.getEvents()){
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

}
