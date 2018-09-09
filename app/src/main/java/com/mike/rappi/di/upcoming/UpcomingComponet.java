package com.mike.rappi.di.upcoming;

import com.mike.rappi.di.PerFragment;
import com.mike.rappi.di.app.AppComponent;
import com.mike.rappi.ui.fragment.UpcomingFragment;
import dagger.Component;

@PerFragment
@Component(modules = UpcomingModule.class, dependencies = AppComponent.class)
public interface UpcomingComponet {
  void inject(UpcomingFragment fragment);
}
