package com.savchits.films.dagger.modules;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.savchits.films.viewModel.AppViewModelFactory;
import com.savchits.films.viewModel.FilmsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by Zakhar Savchits on 19.04.2018.
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(FilmsViewModel.class)
    abstract ViewModel bindFilmsViewModel(FilmsViewModel filmsViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(AppViewModelFactory factory);
}
