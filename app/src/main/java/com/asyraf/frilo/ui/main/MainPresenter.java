package com.asyraf.frilo.ui.main;

import javax.inject.Inject;

import com.asyraf.frilo.data.DataManager;
import com.asyraf.frilo.data.local.PreferencesHelper;
import com.asyraf.frilo.injection.ConfigPersistent;
import com.asyraf.frilo.ui.base.BasePresenter;
import com.asyraf.frilo.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;
    private final PreferencesHelper mPreference;

    @Inject
    public MainPresenter(DataManager dataManager, PreferencesHelper preferencesHelper) {
        mDataManager = dataManager;
        mPreference = preferencesHelper;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    void getParkingLocation(double latitude, double longitude, int vehicle) {
        checkViewAttached();
        getMvpView().showProgress(true);
        mDataManager.getParkLocation(mPreference.getTokenToServer(),latitude,longitude,vehicle)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(parkLocationResponse -> {
                    getMvpView().showProgress(false);
                    if (parkLocationResponse.status == 200){
                        getMvpView().showParkLocation(parkLocationResponse);
                    }else{
                        getMvpView().showServerError(parkLocationResponse.message);
                    }

                }, throwable -> {
                    getMvpView().showProgress(false);
                    getMvpView().showError(throwable);
                });
    }

}