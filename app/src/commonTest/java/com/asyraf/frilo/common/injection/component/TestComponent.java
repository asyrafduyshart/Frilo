package com.asyraf.frilo.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.asyraf.frilo.common.injection.module.ApplicationTestModule;
import com.asyraf.frilo.injection.component.ApplicationComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}