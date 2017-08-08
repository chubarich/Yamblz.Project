package ru.karapetiandav.yamblzproject.di.module;

import android.content.res.Resources;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelperImpl;
import ru.karapetiandav.yamblzproject.data.network.api.CityApi;
import ru.karapetiandav.yamblzproject.data.network.api.CityApiEndpoints;
import ru.karapetiandav.yamblzproject.data.network.api.WeatherApi;
import ru.karapetiandav.yamblzproject.di.qualifiers.WeatherApiBaseUrl;

@Module
public class  NetworkModule {


    @Provides
    @Singleton
    @NonNull
    @WeatherApiBaseUrl
    String provideWeatherBaseUrl(Resources resources) {
//        todo убрать
        return resources.getString(R.string.api_base_url);
    }

    @Provides
    @Singleton
    @NonNull
    WeatherApi provideWeatherApi(@WeatherApiBaseUrl String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build().create(WeatherApi.class);
    }

    @Provides
    @Singleton
    @NonNull
    CityApi provideCityApi() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(CityApiEndpoints.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        return builder.build().create(CityApi.class);
    }

    @Provides
    @Singleton
    @NonNull
    NetworkHelper provideNetworkHelper(WeatherApi weatherApi, CityApi cityApi) {
        return new NetworkHelperImpl(weatherApi, cityApi);
    }
}
