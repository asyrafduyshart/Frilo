package com.asyraf.frilo.ui.login;

import com.asyraf.frilo.ui.base.MvpView;

/**
 * Created by Asyraf Duyshart on 2/22/17.
 */

public interface LoginMvpView extends MvpView {

    void registerSuccess();

    void registerFailed();

    void loginSuccess();

    void loginFailed();

    void showProgress(boolean show);

    void showError(Throwable error);
}
