package com.asyraf.frilo.ui.main;

import javax.inject.Inject;

import com.asyraf.frilo.data.DataManager;
import com.asyraf.frilo.injection.ConfigPersistent;
import com.asyraf.frilo.ui.base.BasePresenter;
import com.asyraf.frilo.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getMvpView().showProgress(true);
        mDataManager.getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(pokemons -> {
                    getMvpView().showProgress(false);
                    getMvpView().showPokemon(pokemons);
                }, throwable -> {
                    getMvpView().showProgress(false);
                    getMvpView().showError(throwable);
                });
    }

}