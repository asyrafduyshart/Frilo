package com.asyraf.frilo.ui.main;

import java.util.List;

import com.asyraf.frilo.data.model.ParkLocationResponse;
import com.asyraf.frilo.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showParkLocation(ParkLocationResponse parkLocationResponse);

    void showProgress(boolean show);

    void showServerError(String message);

    void showError(Throwable error);

}