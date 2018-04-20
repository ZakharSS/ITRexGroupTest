package com.savchits.films.dagger.modules;

import android.arch.lifecycle.ViewModelProvider;

import com.savchits.films.dagger.ActivityScoped;
import com.savchits.films.net.RetrofitService;
import com.savchits.films.room.FilmsDao;
import com.savchits.films.viewModel.AppViewModelFactory;
import com.savchits.films.viewModel.FilmsViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Zakhar Savchits on 19.04.2018.
 */
@Module(includes = ViewModelModule.class)
public class FilmsViewModelModule {

}
