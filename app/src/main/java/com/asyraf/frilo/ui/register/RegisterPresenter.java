package com.asyraf.frilo.ui.register;

import android.util.Log;

import com.asyraf.frilo.data.DataManager;
import com.asyraf.frilo.injection.ConfigPersistent;
import com.asyraf.frilo.ui.base.BasePresenter;
import com.asyraf.frilo.util.rx.scheduler.SchedulerUtils;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.google.firebase.iid.FirebaseInstanceId;

import javax.inject.Inject;

/**
 * Created by Asyraf Duyshart on 2/27/17.
 */

@ConfigPersistent
public class RegisterPresenter extends BasePresenter<RegisterMvpView> {
    private final DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RegisterMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void checkUserToken(){
        //-------- is it already logged in ?
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            Log.d("BaseApplication", ">>>>>>>>>>>> Handle Returning User with token " + String.valueOf(accessToken.getToken()));
            getMvpView().userToken(accessToken.getToken());
        } else {
            getMvpView().userToken(null);
            Log.w("BaseApplication", ">>>>>>>>>>>> Handle new or logged out user");
        }
    }

    public void registerUser(String username,String email,String license,String kit_token){
        String fcmID = FirebaseInstanceId.getInstance().getToken();
        checkViewAttached();
        getMvpView().showProgress(true);
        mDataManager.registerUser(username,email,license,fcmID,kit_token)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(response -> {
                    // It should be always checked if MvpView (Fragment or Activity) is attached.
                    // Calling showProgress() on a not-attached fragment will throw a NPE
                    // It is possible to ask isAdded() in the fragment, but it's better to ask in the presenter
                    getMvpView().showProgress(false);
                    if (response.status == 200){
                        getMvpView().registerSuccess();
                    }else{
                        getMvpView().registerFailed();
                    }
                }, throwable -> {
                    getMvpView().showProgress(false);
                    getMvpView().showError(throwable);
                });
    }


}
