package com.mike.rappi.di.search;

import com.mike.rappi.di.PerActivity;
import com.mike.rappi.di.app.AppComponent;
import com.mike.rappi.ui.activity.MainActivity;
import dagger.Component;

/**
 * Created by mike on 2.03.2017.
 */
@PerActivity
@Component(modules = SearchModule.class, dependencies = AppComponent.class)
public interface SearchComponent {
  void inject(MainActivity activity);
}
