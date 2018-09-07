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
import com.mike.rappi.di.toprated.DaggerTopRatedComponent;
import com.mike.rappi.di.toprated.TopRatedModule;
import com.mike.rappi.model.entity.toprated.TopRatedResults;
import com.mike.rappi.mvp.presenter.toprated.TopRatedPresenter;
import com.mike.rappi.mvp.view.toprated.ITopRatedView;
import com.mike.rappi.ui.adapter.TopRatedRecyclerViewAdapter;
import com.mike.rappi.util.RxBus;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopRatedFragment extends Fragment implements ITopRatedView {

  @BindView(R.id.topratedProgress) ProgressBar topratedProgress;
  @BindView(R.id.toprated_movies_recyclerView) RecyclerView recyclerView;

  @Inject TopRatedPresenter presenter;
  @Inject RxBus bus;

  TopRatedRecyclerViewAdapter adapter;

  public TopRatedFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_top_rated, container, false);
    ButterKnife.bind(this, view);

    initInjector();

    presenter.loadTopRatedMovies();

    return view;
  }

  @Override
  public void showProgress() {
    topratedProgress.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
  }

  @Override
  public void hideProgress() {
    topratedProgress.setVisibility(View.GONE);
    recyclerView.setVisibility(View.VISIBLE);
  }

  @Override
  public void showTopRatedMovies(List<TopRatedResults> topRatedResultsList) {
    adapter = new TopRatedRecyclerViewAdapter(topRatedResultsList, getContext(), bus);
    adapter.notifyDataSetChanged();
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
  }

  private void initInjector() {
    DaggerTopRatedComponent.builder()
        .appComponent(MoviesApp.get(getContext()).getAppComponent())
        .topRatedModule(new TopRatedModule(this))
        .build().inject(this);
  }
}
