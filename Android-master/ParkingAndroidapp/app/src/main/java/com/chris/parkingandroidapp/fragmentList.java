package com.chris.parkingandroidapp;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by chrisantoinette on 10/30/17.
 */

public class fragmentList extends Fragment {

    ListView mListView;
    ParkingDataProvider mDataProvider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_parking_list, container, false);
        mDataProvider = new ParkingDataProvider(getActivity());

        // College location is our current location
        Location currentLocation = new Location("gps");
        currentLocation.setLatitude(37.337011);
        currentLocation.setLongitude(-121.881613);

        CustomListAdapter parkingDataAdapter = new CustomListAdapter(getActivity(),
                mDataProvider.getParkingLocationNames(currentLocation),
                mDataProvider.getParkingAddresses(currentLocation),
                mDataProvider.getParkingDistance(currentLocation));
        mListView = (ListView) rootView.findViewById(R.id.listviewID);
        mListView.setAdapter(parkingDataAdapter);
        return rootView;

    }
}
