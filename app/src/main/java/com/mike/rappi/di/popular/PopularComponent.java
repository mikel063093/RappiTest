package com.mike.rappi.di.popular;

import com.mike.rappi.di.PerFragment;
import com.mike.rappi.di.app.AppComponent;
import com.mike.rappi.ui.fragment.PopularFragment;
import dagger.Component;

/**
 * Created by mike on
 */

@PerFragment
@Component(dependencies = AppComponent.class, modules = PopularModule.class)
public interface PopularComponent {
  void inject(PopularFragment fragment);
}
