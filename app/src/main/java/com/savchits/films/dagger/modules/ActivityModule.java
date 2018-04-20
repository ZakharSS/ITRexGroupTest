package com.savchits.films.dagger.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.savchits.films.dagger.ActivityScoped;
import com.savchits.films.ui.films.FilmsActivity;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Zakhar Savchits on 19.04.2018.
 */
@Module
public abstract class ActivityModule {
    @ActivityScoped
    @Binds
    abstract ViewModelProvider.Factory  ViewModelFactory(ViewModelProvider.Factory viewModelFactory);
}
