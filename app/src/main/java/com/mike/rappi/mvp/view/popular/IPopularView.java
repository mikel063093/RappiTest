package com.mike.rappi.mvp.view.popular;



import com.mike.rappi.model.entity.popular.PopularResults;
import java.util.List;

/**
 * Created by mike
 */

public interface IPopularView {
    void showProgress();

    void hideProgress();

    void showPopularMovies(List<PopularResults> popularResultsList);
}
