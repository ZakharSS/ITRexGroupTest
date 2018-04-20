package com.savchits.films.dagger.modules;

import android.arch.persistence.room.Room;

import com.savchits.films.App;
import com.savchits.films.room.AppDatabase;
import com.savchits.films.room.FilmsDao;

import dagger.Module;
import dagger.Provides;

import static com.savchits.films.Constants.DB_NAME;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
@Module
public class RoomModule {

    @Provides
    AppDatabase provideAppDataBase(App context) {
        return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    FilmsDao providesFilmsDao(AppDatabase database) {
        return database.filmsDao();
    }
}
