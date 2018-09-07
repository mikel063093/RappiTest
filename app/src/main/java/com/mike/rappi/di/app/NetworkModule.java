package com.mike.rappi.di.app;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.util.Constants;
import dagger.Module;
import dagger.Provides;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mike
 */

@Module
public class NetworkModule {

  @Provides
  @Singleton
  HttpLoggingInterceptor provideHttpLoggingInterceptor() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    return interceptor;
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
    List<Protocol> protocols = new ArrayList<>();
    protocols.add(Protocol.HTTP_1_1);
    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    okHttpClient.connectTimeout(1, TimeUnit.MINUTES);
    okHttpClient.readTimeout(1, TimeUnit.MINUTES);
    okHttpClient.protocols(protocols);
    okHttpClient.addInterceptor(httpLoggingInterceptor);
    return okHttpClient.build();
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient okHttpClient) {
    return new Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(okHttpClient)
        .build();
  }

  @Provides
  @Singleton
  ApiSource provideApiSource(Retrofit retrofit) {
    return retrofit.create(ApiSource.class);
  }
}
