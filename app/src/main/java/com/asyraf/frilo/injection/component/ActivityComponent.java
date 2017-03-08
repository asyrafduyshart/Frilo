package com.asyraf.frilo.injection.component;

import dagger.Subcomponent;
import com.asyraf.frilo.injection.PerActivity;
import com.asyraf.frilo.injection.module.ActivityModule;
import com.asyraf.frilo.ui.base.BaseActivity;
import com.asyraf.frilo.ui.detail.DetailActivity;
import com.asyraf.frilo.ui.login.LoginActivity;
import com.asyraf.frilo.ui.main.MainActivity;
import com.asyraf.frilo.ui.register.RegisterActivity;

@PerActivity
@Subcomponent(modules =ActivityModule.class)
public interface ActivityComponent {
    void inject(BaseActivity baseActivity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(LoginActivity loginActivity);

    void inject(RegisterActivity registerActivity);
}
