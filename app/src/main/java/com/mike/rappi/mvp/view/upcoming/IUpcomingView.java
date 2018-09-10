package com.mike.rappi.mvp.view.upcoming;

import com.mike.rappi.model.entity.upcoming.UpcomingResults;
import java.util.List;

/**
 * Created by mike
 */

public interface IUpcomingView {
    void showProgress();

    void hideProgress();

    void showUpComingMovies(List<UpcomingResults> topRatedResultsList);
}
