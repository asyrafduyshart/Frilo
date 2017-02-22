package com.asyraf.frilo.injection.component;

import dagger.Subcomponent;
import com.asyraf.frilo.injection.PerFragment;
import com.asyraf.frilo.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {

}