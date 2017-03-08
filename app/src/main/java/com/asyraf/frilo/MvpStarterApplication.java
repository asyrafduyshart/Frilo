package com.asyraf.frilo;

import android.app.Application;
import android.content.Context;

import com.facebook.accountkit.AccountKit;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;

import com.asyraf.frilo.injection.component.ApplicationComponent;
import com.asyraf.frilo.injection.component.DaggerApplicationComponent;
import com.asyraf.frilo.injection.module.ApplicationModule;
import timber.log.Timber;

public class MvpStarterApplication extends Application {

    ApplicationComponent mApplicationComponent;

    public static MvpStarterApplication get(Context context) {
        return (MvpStarterApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize Facebook Account Kit
        AccountKit.initialize(getApplicationContext(), () -> Timber.v("Account Kit Initialized"));

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Stetho.initializeWithDefaults(this);
            LeakCanary.install(this);
        }
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
