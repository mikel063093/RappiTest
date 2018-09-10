package com.mike.rappi;

import android.app.Application;
import android.content.Context;



import com.mike.rappi.di.app.AppComponent;
import com.mike.rappi.di.app.AppModule;
import com.mike.rappi.di.app.DaggerAppComponent;
import com.mike.rappi.di.app.NetworkModule;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by mike
 */

public class MoviesApp extends Application {

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    initInjector();
    Realm.init(this);

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }

  private void initInjector() {
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .networkModule(new NetworkModule())
        .build();
  }

  public static MoviesApp get(Context context) {
    return (MoviesApp) context.getApplicationContext();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
