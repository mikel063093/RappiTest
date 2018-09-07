package com.mike.rappi.mvp.view.toprated;



import com.mike.rappi.model.entity.toprated.TopRatedResults;
import java.util.List;

/**
 * Created by mike
 */

public interface ITopRatedView {
    void showProgress();

    void hideProgress();

    void showTopRatedMovies(List<TopRatedResults> topRatedResultsList);
}
