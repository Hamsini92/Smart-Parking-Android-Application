package com.chris.parkingandroidapp;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.LocationListener;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


/**
 * Created on 11/6/17.
 */

public class fragmentMap extends Fragment  implements LocationListener{

    MapView mMapView;
    ParkingDataProvider mDataProvider;
    private Fragment mFragment;
    private GoogleMap googleMap;
    private String TAG = fragmentMap.class.getName();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_fragment_parking_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.parkingmap);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);
                showMap();

            }
        });
        mDataProvider = new ParkingDataProvider(getActivity());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void showMap() {
        // Enable Zoom
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        //set Map TYPE
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //enable Current location Button
        googleMap.setMyLocationEnabled(true);

        LocationManager mLocationManager = (LocationManager)getActivity().getApplicationContext().getSystemService(LOCATION_SERVICE);
        List<String> providers = mLocationManager.getProviders(true);
        Location bestLocation = null;
        String bestProvider = new String();
        for (String provider : providers) {
            Location l = mLocationManager.getLastKnownLocation(provider);
            if (l == null) {
                continue;
            }
            if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                // Found best last known location: %s", l);
                bestLocation = l;
                bestProvider = provider;
            }
        }
        if (bestLocation != null) {
            onLocationChanged(bestLocation);
            // Register for location changes only if location is available.
            mLocationManager.requestLocationUpdates(bestProvider, 5000, 0,  this);
        }

        // Get this location's parking spots

        List<LatLng> parkingLocations = mDataProvider.getParkingLocations(bestLocation);
        plotParkingLocations(parkingLocations);
    }
    private void plotParkingLocations(List<LatLng> parkingLocations) {
        for(int i = 0; i < parkingLocations.size(); i++) {
            googleMap.addMarker(new MarkerOptions().position(parkingLocations.get(i)).title("Parking Spot: "+ i));
        }
    }
    // Location Changed
    @Override
    public void onLocationChanged(Location location) {

        Marker marker = null;
        double latitude = location.getLatitude();
        double longitude =location.getLongitude();

        LatLng loc = new LatLng(latitude, longitude);

        if (marker!=null){
            marker.remove();
        }

        //marker =  googleMap.addMarker(new MarkerOptions().position(loc).title("Parking Spot"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.0f));
    }
    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getActivity().getBaseContext(), "Gps is turned off!!",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getActivity().getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int number, Bundle bundle) {

    }
}