package com.mike.rappi.di.search;

import com.mike.rappi.di.PerActivity;
import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.presenter.search.SearchPresenter;
import com.mike.rappi.mvp.view.search.ISearchView;
import com.mike.rappi.ui.activity.MainActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mike
 */

@Module
public class SearchModule {

  MainActivity activity;
  ISearchView iSearchView;

  public SearchModule(ISearchView iSearchView) {
    this.iSearchView = iSearchView;
  }

  @Provides
  @PerActivity
  MainActivity provideMainActivity() {
    return activity;
  }

  @Provides
  @PerActivity
  SearchPresenter provideSearchPresenter(ApiSource apiSource) {
    return new SearchPresenter(iSearchView, apiSource);
  }
}

