package com.savchits.films.dagger.modules;

import android.content.Context;

import com.savchits.films.App;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
@Module()
public abstract class AppModule {
    @Binds
    abstract Context bindContext(App application);
}
