package com.mike.rappi.di.detail;

import com.mike.rappi.di.PerActivity;
import com.mike.rappi.di.app.AppComponent;
import com.mike.rappi.ui.activity.MovieDetailActivity;
import dagger.Component;

/**
 * Created by mike on 2.03.2017.
 */
@PerActivity
@Component(modules = DetailModule.class, dependencies = AppComponent.class)
public interface DetailComponent {
  void inject(MovieDetailActivity activity);
}
