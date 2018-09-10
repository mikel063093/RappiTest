package com.mike.rappi.mvp.presenter.search;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.presenter.upcoming.IUpcomingPresenter;
import com.mike.rappi.mvp.view.search.ISearchView;
import com.mike.rappi.mvp.view.upcoming.IUpcomingView;
import com.mike.rappi.util.Constants;
import io.realm.Realm;
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
    realm = Realm.getDefaultInstance();
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
            e -> Timber.e(e.getMessage()));
  }
}
