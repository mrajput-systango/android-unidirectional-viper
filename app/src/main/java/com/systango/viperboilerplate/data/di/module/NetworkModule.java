package com.systango.viperboilerplate.data.di.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.systango.viperboilerplate.BuildConfig;
import com.systango.viperboilerplate.data.di.ApplicationScope;
import com.systango.viperboilerplate.data.network.ApiCallInterface;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mohit Rajput on 12/2/19.
 */
@Module
public class NetworkModule {

    private String apiKey;

    public NetworkModule(String apiKey) {
        this.apiKey = apiKey;
    }

    @Provides
    @ApplicationScope
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();
        return builder.setLenient().create();
    }

    @Provides
    @ApplicationScope
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @ApplicationScope
    ApiCallInterface getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(ApiCallInterface.class);
    }

    @Provides
    @ApplicationScope
    OkHttpClient getRequestHeader() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(httpLoggingInterceptor);
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder().build();
            HttpUrl originalHttpUrl = request.url();
            HttpUrl completeUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", apiKey)
                    .build();
            request = original.newBuilder()
                    .url(completeUrl).build();

            return chain.proceed(request);
        })
                .connectTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);

        return httpClient.build();
    }
}
