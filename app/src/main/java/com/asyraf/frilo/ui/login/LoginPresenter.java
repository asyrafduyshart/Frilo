package com.asyraf.frilo.ui.login;

import com.asyraf.frilo.injection.ConfigPersistent;
import com.asyraf.frilo.ui.base.BasePresenter;

import javax.inject.Inject;

/**
 * Created by Asyraf Duyshart on 2/22/17.
 */

@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void loginFirebaseEmail(String email, String password){
        checkViewAttached();
        getMvpView().showProgress(true);
    }

}
