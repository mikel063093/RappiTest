package com.mike.rappi.mvp.presenter.search;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.model.entity.popular.PopularResults;
import com.mike.rappi.model.entity.search.Movie;
import com.mike.rappi.model.entity.search.MoviesResponse;
import com.mike.rappi.mvp.presenter.upcoming.IUpcomingPresenter;
import com.mike.rappi.mvp.view.search.ISearchView;
import com.mike.rappi.mvp.view.upcoming.IUpcomingView;
import com.mike.rappi.util.Constants;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by mike
 */

public class SearchPresenter implements ISearchPresenter {

  private ISearchView view;
  private ApiSource apiSource;
  private Realm realm;

  @Inject
  public SearchPresenter(ISearchView view, ApiSource apiSource) {
    this.view = view;
    this.apiSource = apiSource;
    this.realm = Realm.getDefaultInstance();
  }

  @Override public void searchMovie(String query) {
    apiSource.searchMovie(Constants.API_KEY, Locale.getDefault().getLanguage(), query)
        .debounce(300, MILLISECONDS)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(topRatedResponse -> {
              view.showSearchResponse(topRatedResponse);
              realm.executeTransactionAsync(
                  realm1 -> realm1.insertOrUpdate(topRatedResponse.getResults()),
                  () -> Timber.e("onSucces"), Timber::e);
            },
            e -> {
              List<Movie> result = realm.copyFromRealm(realm.where(Movie.class).findAll());
              MoviesResponse moviesResponse = new MoviesResponse();
              moviesResponse.setResults(result);
              view.showSearchResponse(moviesResponse);
              Timber.e(e);
            });
  }
}
