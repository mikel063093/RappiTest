package com.mike.rappi.di.toprated;



import com.mike.rappi.di.PerFragment;
import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.mvp.presenter.toprated.TopRatedPresenter;
import com.mike.rappi.mvp.view.toprated.ITopRatedView;
import com.mike.rappi.ui.fragment.TopRatedFragment;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mike
 */

@Module
public class TopRatedModule {

    ITopRatedView topRatedView;
    TopRatedFragment fragment;

    public TopRatedModule(ITopRatedView topRatedView) {
        this.topRatedView = topRatedView;
    }

    @Provides
    @PerFragment
    TopRatedFragment provideFragment() {
        return fragment;
    }
    @Provides
    @PerFragment
    TopRatedPresenter provideTopRatedPresenter(ApiSource apiSource) {
        return new TopRatedPresenter(topRatedView,apiSource);
    }
}
