package com.example.gadsleaderboard.services;


import android.os.Build;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    private static final String URL= "https://gadsapi.herokuapp.com/";
    private static final String NETWORK_POST_URL= "https://docs.google.com/forms/d/e/";

    //create Logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor(  ).setLevel( HttpLoggingInterceptor.Level.BODY );
    //create OKHttp Client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                    .readTimeout( 15, TimeUnit.SECONDS )
                    .addInterceptor( logger );


    private static Retrofit.Builder builder=
            new Retrofit.Builder().baseUrl( URL ).
                    addConverterFactory( GsonConverterFactory.create() )
                    .client( okHttp.build() );

    private static Retrofit retrofit = builder.build();

    public static <S> S buildService(Class<S> serviceType){
        return retrofit.create( serviceType );
    }



    //Network post
    private static Retrofit.Builder postBuilder=
            new Retrofit.Builder().baseUrl( NETWORK_POST_URL ).
                    addConverterFactory( GsonConverterFactory.create() )
                    .client( okHttp.build() );

    private static Retrofit postRetrofit = postBuilder.build();

    public static <S> S buildPostService(Class<S> serviceType){
        return postRetrofit.create( serviceType );
    }


}

