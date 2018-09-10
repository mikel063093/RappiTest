package com.mike.rappi.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.google.android.material.tabs.TabLayout;
import com.mike.rappi.MoviesApp;
import com.mike.rappi.R;
import com.mike.rappi.di.search.DaggerSearchComponent;
import com.mike.rappi.di.search.SearchModule;
import com.mike.rappi.model.entity.search.MoviesResponse;
import com.mike.rappi.mvp.presenter.search.SearchPresenter;
import com.mike.rappi.mvp.view.search.ISearchView;
import com.mike.rappi.ui.adapter.AutoCompleteAdapter;
import com.mike.rappi.ui.adapter.ViewPagerAdapter;
import com.mike.rappi.ui.fragment.PopularFragment;
import com.mike.rappi.ui.fragment.TopRatedFragment;
import com.mike.rappi.ui.fragment.UpcomingFragment;
import com.mike.rappi.util.RxBus;
import java.util.Objects;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ISearchView {

  @Inject SearchPresenter presenter;
  @Inject RxBus bus;

  @BindView(R.id.mainToolbar) Toolbar toolbar;
  @BindView(R.id.tabs) TabLayout tabLayout;
  @BindView(R.id.viewPager) ViewPager viewPager;

  private SearchView searchView;
  private AutoCompleteAdapter adapter;
  private SearchView.SearchAutoComplete searchAutoComplete;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initInjector();
    setSupportActionBar(toolbar);
    Objects.requireNonNull(getSupportActionBar()).setHomeButtonEnabled(true);

    setupViewPager(viewPager);
    tabLayout.setupWithViewPager(viewPager);
  }

  @SuppressLint("RestrictedApi") @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.search_menu, menu);
    MenuItem search = menu.findItem(R.id.search);
    searchView = (SearchView) search.getActionView();
    searchAutoComplete = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
    searchAutoComplete.setThreshold(0);
    initSearch();
    return super.onCreateOptionsMenu(menu);
  }

  @Override public void showSearchResponse(MoviesResponse moviesResponse) {
    adapter =
        new AutoCompleteAdapter(this.getApplicationContext(), moviesResponse.getResults(), bus);
    searchAutoComplete.setAdapter(adapter);
    searchAutoComplete.setOnItemClickListener((adapterView, view, i, l) -> {
      Log.d("setOnItemClickListener", moviesResponse.getResults().get(i).getTitle());
    });
  }

  private void initSearch() {
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
      @Override public boolean onQueryTextSubmit(String query) {
        Log.d("query", query);
        presenter.searchMovie(query);
        return query != null;
      }

      @Override public boolean onQueryTextChange(String query) {
        Log.d("onQueryTextChange", query);
        if (query.length() >= 4) presenter.searchMovie(query);
        return query != null;
      }
    });
  }

  private void initInjector() {
    DaggerSearchComponent.builder()
        .appComponent(MoviesApp.get(this).getAppComponent())
        .searchModule(new SearchModule(this))
        .build().inject(this);
  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new PopularFragment(), "Popular");
    adapter.addFragment(new TopRatedFragment(), "Top Rated");
    adapter.addFragment(new UpcomingFragment(), "Upcoming");
    viewPager.setAdapter(adapter);
  }
}
