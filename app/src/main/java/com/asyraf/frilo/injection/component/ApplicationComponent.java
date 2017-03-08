package com.asyraf.frilo.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.asyraf.frilo.data.DataManager;
import com.asyraf.frilo.data.FirebaseManager;
import com.asyraf.frilo.data.remote.MvpStarterService;
import com.asyraf.frilo.injection.ApplicationContext;
import com.asyraf.frilo.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager dataManager();

    FirebaseManager firebasemanager();

    MvpStarterService mvpBoilerplateService();
}
