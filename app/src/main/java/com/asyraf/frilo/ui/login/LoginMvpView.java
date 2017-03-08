package com.asyraf.frilo.ui.login;

import com.asyraf.frilo.ui.base.MvpView;

/**
 * Created by Asyraf Duyshart on 2/22/17.
 */

public interface LoginMvpView extends MvpView {

    void loginSuccess();

    void loginFailed();

    void needRegister();

    void alreadyFacebookVerified(boolean verified);

    void showProgress(boolean show);

    void showError(Throwable error);
}
