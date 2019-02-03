package com.openmobility.bike14;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

public class MapFragment extends Fragment implements
        GoogleMap.OnMarkerClickListener
    {

    MapView mapView;
    GoogleMap googleMap;
    LatLng myPosition;

    List<LatLng> trees;
    List<LatLng> streensGreen;
    List<LatLng> streensRed;
    List<LatLng> streensYellow;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkLocationPermission();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container, false);
        mapView = v.findViewById(R.id.mapsView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                googleMap.setMyLocationEnabled(true);
                googleMap.setOnMarkerClickListener(MapFragment.this);


                //mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );


                LocationManager locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                Criteria criteria = new Criteria();

                // Getting the name of the best provider
                String provider = locationManager.getBestProvider(criteria, true);

                // Getting Current Location
                Location location = locationManager.getLastKnownLocation(provider);

                if (location != null) {
                    // Getting latitude of the current location
                    double latitude = location.getLatitude();

                    // Getting longitude of the current location
                    double longitude = location.getLongitude();

                    // Creating a LatLng object for the current location
                    LatLng latLng = new LatLng(latitude, longitude);

                    myPosition = new LatLng(latitude, longitude);

                    CameraPosition cameraPosition = new CameraPosition.Builder().target(myPosition).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

                loadData();
            }
        });


        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void loadData() {
        trees = new ArrayList<LatLng>();
        trees.add(new LatLng(-8.057983, -34.882757));

        streensGreen = new ArrayList<LatLng>();
        streensRed = new ArrayList<LatLng>();
        streensRed.add(new LatLng(-8.064302, -34.873590));
        streensRed.add(new LatLng(-8.060074, -34.872700));
        streensRed.add(new LatLng(-8.050900, -34.872523));

        streensYellow = new ArrayList<LatLng>();
        streensYellow.add(new LatLng(-8.067202, -34.873955));


        streensGreen.add(new LatLng(-8.061789, -34.871901));
        streensGreen.add(new LatLng(-8.062851, -34.871804));
        streensRed.add(new LatLng(-8.062830, -34.872287));

        for(LatLng latLng : trees) {
           googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Arvore")
                    .snippet("Baboa")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bike3)));
        }

        for(LatLng latLng : streensGreen) {
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bike6)));
        }

        for(LatLng latLng : streensRed) {
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bike4)));
        }

        for(LatLng latLng : streensYellow) {
            googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.bike5)));
        }

    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }

        @Override
        public boolean onMarkerClick(Marker marker) {
            return false;
        }


    }
