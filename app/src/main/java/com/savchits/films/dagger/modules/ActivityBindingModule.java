package com.savchits.films.dagger.modules;

import com.savchits.films.dagger.ActivityScoped;
import com.savchits.films.ui.films.FilmsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Zakhar Savchits on 19.04.2018.
 */
@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = ViewModelModule.class)
    abstract FilmsActivity filmsActivity();
}
