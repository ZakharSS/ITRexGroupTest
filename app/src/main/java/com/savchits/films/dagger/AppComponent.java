package com.savchits.films.dagger;

import com.savchits.films.App;
import com.savchits.films.dagger.modules.ActivityBindingModule;
import com.savchits.films.dagger.modules.AppModule;
import com.savchits.films.dagger.modules.FilmsViewModelModule;
import com.savchits.films.dagger.modules.NetworkModule;
import com.savchits.films.dagger.modules.RoomModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, NetworkModule.class, RoomModule.class, ActivityBindingModule.class, FilmsViewModelModule.class})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        AppComponent.Builder application(App application);

        AppComponent build();
    }
}
