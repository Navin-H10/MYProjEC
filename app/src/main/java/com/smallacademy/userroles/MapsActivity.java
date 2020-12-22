package com.smallacademy.userroles;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<LatLng>arrayList= new ArrayList<LatLng>();
    LatLng nagavara = new LatLng(13.043277535587569, 77.60896913976781);
    LatLng yelahanka = new LatLng(13.097566358111635, 77.59436223952237);
    LatLng kodigehalli = new LatLng(13.05709296004672, 77.59302591163006);
    LatLng sahakanagar = new LatLng(13.0595176837058, 77.5865886096604);
    LatLng rajajinagar = new LatLng(12.993211302128922, 77.55755597021528);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(nagavara).title("Marker in nagavara"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(nagavara));

        mMap.addMarker(new MarkerOptions().position(yelahanka).title("Marker in yelahanka"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(yelahanka));

        mMap.addMarker(new MarkerOptions().position(kodigehalli).title("Marker in kodigehalli"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kodigehalli));

        mMap.addMarker(new MarkerOptions().position(sahakanagar).title("Marker in sahakarnagar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sahakanagar));

        mMap.addMarker(new MarkerOptions().position(rajajinagar).title("Marker in rajajinagar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(rajajinagar));
    }
}