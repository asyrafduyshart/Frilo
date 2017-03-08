package com.asyraf.frilo.ui.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.asyraf.frilo.data.DataManager;
import com.asyraf.frilo.data.FirebaseManager;
import com.asyraf.frilo.injection.ConfigPersistent;
import com.asyraf.frilo.ui.base.BasePresenter;
import com.asyraf.frilo.util.rx.scheduler.SchedulerUtils;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitLoginResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Asyraf Duyshart on 2/22/17.
 */

@ConfigPersistent
public class LoginPresenter extends BasePresenter<LoginMvpView> {

    DataManager mDataManager;

    @Inject
    public LoginPresenter(DataManager mDataManager) {
        this.mDataManager = mDataManager;
    }

    @Override
    public void attachView(LoginMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void isFacebookVerified() {
        //-------- is it already logged in ?
        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if (accessToken != null) {
            Log.d("BaseApplication", ">>>>>>>>>>>> Handle Returning User with token " + String.valueOf(accessToken.getToken()));
            getMvpView().alreadyFacebookVerified(true);
            checkServerVerified(accessToken.getToken());
        } else {
            getMvpView().alreadyFacebookVerified(false);
            Log.w("BaseApplication", ">>>>>>>>>>>> Handle new or logged out user");
        }

    }

    public void checkServerVerified(String kit_token) {
        //------------is server already login?
        checkViewAttached();
        getMvpView().showProgress(true);
        mDataManager.authServer(kit_token)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(authResponse -> {
                    getMvpView().showProgress(false);
                    if (authResponse.status == 200) {
                        getMvpView().loginSuccess();
                    } else if (authResponse.status == 404) {
                        getMvpView().needRegister();
                    } else {
                        getMvpView().loginFailed();
                    }
                }, throwable -> {
                    getMvpView().showProgress(false);
                    getMvpView().showError(throwable);
                });
    }

    public void authResult(AccountKitLoginResult loginResult) {
        String errorMessage;
        if (loginResult.getError() != null) {
            getMvpView().loginFailed();
            errorMessage = loginResult.getError().getErrorType().getMessage();
            // or show a dialog / fragment etc
        } else if (loginResult.wasCancelled()) {
            errorMessage = "Login Cancelled";
            getMvpView().loginFailed();
        } else {
            if (loginResult.getAccessToken() != null) {
                errorMessage = "Success:" + loginResult.getAccessToken().getAccountId();
                checkServerVerified(loginResult.getAccessToken().getToken());
                Timber.v(loginResult.getAccessToken().getAccountId());
            } else {
                errorMessage = String.format("Success:%s...", loginResult.getAuthorizationCode().substring(0, 10));
                getMvpView().loginFailed();
            }
            // If you have an authorization code, retrieve it from
            // loginResult.getAuthorizationCode()
            // and pass it to your server and exchange it for an access token.

            // Success! Start your next activity...
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    }

}
