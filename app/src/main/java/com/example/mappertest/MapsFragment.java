package com.example.mappertest;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class MapsFragment extends Fragment {

    private GoogleMap mMap;
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);

//
//            super.onCreate(savedInstanceState);
//
//
//            // initializing our search view.
////            searchView = view.findViewById(R.id.idSearchView);
//
//
//            // adding on query listener for our search view.
//            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                @Override
//                public boolean onQueryTextSubmit(String query) {
//                    // on below line we are getting the
//                    // location name from search view.
//                    String location = searchView.getQuery().toString();
//
//                    // below line is to create a list of address
//                    // where we will store the list of all address.
//                    List<Address> addressList = null;
//
//                    // checking if the entered location is null or not.
//                    if (location != null || location.equals("")) {
//                        // on below line we are creating and initializing a geo coder.
//                        Geocoder geocoder = new Geocoder(getContext());
//                        try {
//                            // on below line we are getting location from the
//                            // location name and adding that location to address list.
//                            addressList = geocoder.getFromLocationName(location, 1);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        // on below line we are getting the location
//                        // from our list a first position.
//                        Address address = addressList.get(0);
//
//                        // on below line we are creating a variable for our location
//                        // where we will add our locations latitude and longitude.
//                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//
//                        // on below line we are adding marker to that position.
//                        mMap.addMarker(new MarkerOptions().position(latLng).title(location));
//
//                        // below line is to animate camera to that position.
//                        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                    }
//                    return false;
//                }
//
//                @Override
//                public boolean onQueryTextChange(String newText) {
//                    return false;
//                }
//            });
//            // at last we calling our map fragment to update.
//
//
//
//
//



        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                mMap = googleMap;
                GoogleMapOptions options = new GoogleMapOptions();
                options.mapType(GoogleMap.MAP_TYPE_NORMAL)
                        .compassEnabled(true);
                LatLng kerrHall = new LatLng(43.65877, -79.37928);
                mMap.addMarker(new MarkerOptions()
                        .position(kerrHall)
                        .title("Kerr Hall Quad"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(kerrHall, 17.8f));
                mMap.setTrafficEnabled(false);
            }
        });

        return view;
    }
}