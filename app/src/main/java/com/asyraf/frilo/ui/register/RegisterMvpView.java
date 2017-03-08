package com.asyraf.frilo.ui.register;

import com.asyraf.frilo.ui.base.MvpView;

/**
 * Created by Asyraf Duyshart on 2/27/17.
 */

public interface RegisterMvpView extends MvpView {
    void registerSuccess();

    void registerFailed();

    void userToken(String kit_token);

    void showProgress(boolean show);

    void showError(Throwable error);

}
