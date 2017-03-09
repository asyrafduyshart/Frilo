package com.asyraf.frilo.ui.main;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;


import javax.inject.Inject;

import butterknife.BindView;

import com.asyraf.frilo.Manifest;
import com.asyraf.frilo.R;
import com.asyraf.frilo.data.model.ParkLocationResponse;
import com.asyraf.frilo.ui.base.BaseActivity;
import com.asyraf.frilo.ui.common.ErrorView;
import com.asyraf.frilo.util.Utils;
import com.github.nitrico.mapviewpager.MapViewPager;
import com.google.android.gms.maps.SupportMapFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

import de.mateware.snacky.Snacky;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView,
        ErrorView.ErrorListener, MapViewPager.Callback {

    @Inject
    MainPresenter mMainPresenter;

    @BindView(R.id.view_error)
    ErrorView mErrorView;
    @BindView(R.id.progress)
    ProgressBar mProgress;
    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private MapViewPager mvp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        mMainPresenter.attachView(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(granted ->{
                    if (granted){

                    }else{

                    }

                });

        setSupportActionBar(mToolbar);

        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.white);
        mSwipeRefreshLayout.setOnRefreshListener(() -> mMainPresenter.getParkingLocation(-6.17732,106.8105243,1));

        mErrorView.setErrorListener(this);

        mMainPresenter.getParkingLocation(-6.17732,106.8105243,1);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }

    @Override
    public void showParkLocation(ParkLocationResponse parkLocationResponse) {

        viewPager.setPageMargin(Utils.dp(this,10));
        Utils.setMargins(viewPager, 0, 0, 0, Utils.getNavigationBarHeight(this));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mvp = new MapViewPager.Builder(this)
                .mapFragment(mapFragment)
                .viewPager(viewPager)
                .position(1)
                .adapter(new MainMapAdapter(getSupportFragmentManager(),parkLocationResponse))
                .callback(this)
                .build();


        mSwipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            mErrorView.setVisibility(View.GONE);
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            mProgress.setVisibility(View.GONE);
        }
    }

    @Override
    public void showServerError(String message) {
        Snacky.builder().setActivty(this).info().setText(message).setAction("Add Vehicle", v -> {

        }).setDuration(Snacky.LENGTH_INDEFINITE).show();
    }

    @Override
    public void showError(Throwable error) {
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mErrorView.setVisibility(View.VISIBLE);
        Timber.e(error, "There was an error retrieving the pokemon");
    }

    @Override
    public void onReloadData() {
        mMainPresenter.getParkingLocation(-6.17732,106.8105243,1);
    }

    @Override
    public void onMapViewPagerReady() {
        mvp.getMap().setPadding(
                0,
                Utils.dp(this, 40),
                Utils.getNavigationBarWidth(this),
                viewPager.getHeight() + Utils.getNavigationBarHeight(this));
    }
}