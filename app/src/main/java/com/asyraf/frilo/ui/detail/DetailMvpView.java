package com.asyraf.frilo.ui.detail;

import com.asyraf.frilo.data.model.Pokemon;
import com.asyraf.frilo.data.model.Statistic;
import com.asyraf.frilo.ui.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Pokemon pokemon);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);

}