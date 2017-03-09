package com.asyraf.frilo.ui.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asyraf.frilo.R;
import com.asyraf.frilo.data.model.DataItem;
import com.asyraf.frilo.data.model.ParkLocationResponse;
import com.asyraf.frilo.ui.base.BaseFragment;

import butterknife.BindView;


/**
 * Created by Asyraf Duyshart on 3/9/17.
 */

public class MainMapFragment extends BaseFragment {

    @BindView(R.id.title) TextView title;
    @BindView(R.id.tv_location_description) TextView desc;

    public MainMapFragment(){}

    public static MainMapFragment newInstance (int i, String locationDescription,String locationTitle){
        MainMapFragment f = new MainMapFragment();
        Bundle args = new Bundle();
        args.putInt("INDEX", i);
        args.putString("TITLE",locationTitle);
        args.putString("DESC",locationDescription);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle !=null){
            title.setText(bundle.getString("TITLE"));
            desc.setText(bundle.getString("DESC"));
        }
        ViewCompat.setElevation(getView(), 10f);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_main_map;
    }

}
