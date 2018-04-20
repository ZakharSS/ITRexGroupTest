package com.savchits.films.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.savchits.films.net.ApiInterface;
import com.savchits.films.net.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.savchits.films.Constants.BASE_URL;

/**
 * Created by Zakhar Savchits on 13.04.2018.
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiInterface provideNetworkService(Retrofit retrofit) {
        return retrofit.create(ApiInterface.class);
    }

    @Provides
    @Singleton
    RetrofitService provideService(ApiInterface apiInterface) {
        return new RetrofitService(apiInterface);
    }
}
