package com.asyraf.frilo.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.asyraf.frilo.data.model.ParkLocationResponse;
import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Asyraf Duyshart on 3/9/17.
 */

public class MainMapAdapter extends MapViewPager.Adapter {
    private ParkLocationResponse mParkResponse;

    MainMapAdapter(FragmentManager fm, ParkLocationResponse parkLocationResponse) {
        super(fm);
        this.mParkResponse = parkLocationResponse;
    }

    @Override
    public int getCount() {
        return mParkResponse.data.size();
    }

    @Override
    public Fragment getItem(int position) {
        return MainMapFragment.newInstance(position,mParkResponse.data.get(position).locationDesc, mParkResponse.data.get(position).locationName);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mParkResponse.data.get(position).locationName;
    }

    @Override
    public CameraPosition getCameraPosition(int position) {
        return CameraPosition.fromLatLngZoom(new LatLng(mParkResponse.data.get(position).latitude,mParkResponse.data.get(position).longitude), 18f);
    }

}
