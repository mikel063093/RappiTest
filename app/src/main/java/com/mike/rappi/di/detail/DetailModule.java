package com.mike.rappi.di.detail;

import com.mike.rappi.di.PerActivity;
import com.mike.rappi.ui.activity.MovieDetailActivity;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mike
 */

@Module
public class DetailModule {

  MovieDetailActivity activity;

  @Provides
  @PerActivity
  MovieDetailActivity provideDetailActivity() {
    return activity;
  }
}
