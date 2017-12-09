package com.chris.parkingandroidapp;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by chrisantoinette on 11/11/17.
 */

public class ParkingData {
    private String parkingName;
    private String parkingAddress;
    private LatLng parkingSpotLocation;

    public void setParkingSpotLocation(LatLng parkingSpotLocation) {
        this.parkingSpotLocation = parkingSpotLocation;
    }

    public void setParkingAddress(String parkingAddress) {
        this.parkingAddress = parkingAddress;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public LatLng getParkingSpotLocation() {
        return parkingSpotLocation;
    }

    public String getParkingAddress() {
        return parkingAddress;
    }

    public String getParkingName() {
        return parkingName;
    }

    ParkingData(String pName, String pAddress, LatLng pSpot) {
        this.parkingAddress = pAddress;
        this.parkingName = pName;
        this.parkingSpotLocation = pSpot;

    }
}
