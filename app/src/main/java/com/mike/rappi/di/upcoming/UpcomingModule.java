package com.mike.rappi.di.upcoming;

import com.mike.rappi.di.PerFragment;
import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.presenter.toprated.TopRatedPresenter;
import com.mike.rappi.mvp.presenter.upcoming.UpcomingPresenter;
import com.mike.rappi.mvp.view.toprated.ITopRatedView;
import com.mike.rappi.mvp.view.upcoming.IUpcomingView;
import com.mike.rappi.ui.fragment.TopRatedFragment;
import com.mike.rappi.ui.fragment.UpcomingFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mike
 */

@Module
public class UpcomingModule {

  IUpcomingView view;
  UpcomingFragment fragment;

  public UpcomingModule(IUpcomingView view) {
    this.view = view;
  }

  @Provides
  @PerFragment
  UpcomingFragment provideFragment() {
    return fragment;
  }

  @Provides
  @PerFragment
  UpcomingPresenter provideTopRatedPresenter(ApiSource apiSource) {
    return new UpcomingPresenter(view, apiSource);
  }
}
