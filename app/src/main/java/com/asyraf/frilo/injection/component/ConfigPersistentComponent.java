package com.asyraf.frilo.injection.component;

import dagger.Component;
import com.asyraf.frilo.injection.ConfigPersistent;
import com.asyraf.frilo.injection.module.ActivityModule;
import com.asyraf.frilo.injection.module.FragmentModule;
import com.asyraf.frilo.ui.base.BaseActivity;
import com.asyraf.frilo.ui.base.BaseFragment;

/**
 * A dagger component that will live during the lifecycle of an Activity or Fragment but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} and {@link BaseFragment} to
 * see how this components survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    FragmentComponent fragmentComponent(FragmentModule fragmentModule);

}
