package com.mike.rappi.di.app;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.util.RxBus;
import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mike
 */

@Singleton
@Component(modules = { AppModule.class, NetworkModule.class })
public interface AppComponent {
  ApiSource apiSource();

  RxBus rxbus();
}
