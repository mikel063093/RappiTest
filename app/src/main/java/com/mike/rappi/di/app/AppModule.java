package com.mike.rappi.di.app;

import android.app.Application;
import android.content.Context;
import com.mike.rappi.util.RxBus;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by mike
 */

@Module
public class AppModule {
  private Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Singleton
  @Provides
  Application provideApplication() {
    return application;
  }

  @Singleton
  @Provides
  Context provideAppContext() {
    return application.getApplicationContext();
  }

  @Singleton
  @Provides
  RxBus provideRxBus() {
    return RxBus.getInstance();
  }
}
