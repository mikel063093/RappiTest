package com.mike.rappi.ui.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.mike.rappi.R;

import com.mike.rappi.MoviesApp;
import com.mike.rappi.di.popular.DaggerPopularComponent;
import com.mike.rappi.di.popular.PopularModule;
import com.mike.rappi.model.entity.popular.PopularResults;
import com.mike.rappi.mvp.presenter.popular.PopularPresenter;
import com.mike.rappi.mvp.view.popular.IPopularView;
import com.mike.rappi.ui.adapter.PopularRecyclerViewAdapter;
import com.mike.rappi.util.RxBus;
import java.util.List;

import java.util.Objects;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PopularFragment extends Fragment implements IPopularView {

  @BindView(R.id.popular_movies_recyclerView) RecyclerView recyclerView;
  @BindView(R.id.popularProgress) ProgressBar progressBar;

  @Inject PopularPresenter presenter;
  @Inject RxBus bus;

  private PopularRecyclerViewAdapter adapter;

  public PopularFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_popular, container, false);
    ButterKnife.bind(this, view);

    initInjector();

    presenter.loadPopularMovies();

    return view;
  }

  @Override
  public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
  }

  @Override
  public void hideProgress() {
    progressBar.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showPopularMovies(List<PopularResults> popularResultsList) {
    adapter = new PopularRecyclerViewAdapter(popularResultsList, getContext(), bus);
    adapter.notifyDataSetChanged();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
  }

  private void initInjector() {
    DaggerPopularComponent.builder()
        .appComponent(MoviesApp.get(Objects.requireNonNull(this.getContext())).getAppComponent())
        .popularModule(new PopularModule(this))
        .build().inject(this);
  }
}
