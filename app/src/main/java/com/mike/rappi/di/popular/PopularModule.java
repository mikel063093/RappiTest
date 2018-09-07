package com.mike.rappi.di.popular;

import com.mike.rappi.di.PerFragment;
import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.presenter.popular.PopularPresenter;
import com.mike.rappi.mvp.view.popular.IPopularView;
import com.mike.rappi.ui.fragment.PopularFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mike on
 */

@Module
public class PopularModule {

  IPopularView popularView;
  PopularFragment popularFragment;

  public PopularModule(IPopularView popularView) {
    this.popularView = popularView;
  }

  @Provides
  @PerFragment
  PopularFragment provideFragment() {
    return popularFragment;
  }

  @Provides
  @PerFragment
  PopularPresenter providePopularPresenter(ApiSource apiSource) {
    return new PopularPresenter(popularView, apiSource);
  }
}
