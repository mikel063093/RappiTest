package com.mike.rappi.di.toprated;



import com.mike.rappi.di.PerFragment;
import com.mike.rappi.di.app.AppComponent;
import com.mike.rappi.ui.fragment.TopRatedFragment;
import dagger.Component;

/**
 * Created by mike
 */

@PerFragment
@Component(modules = TopRatedModule.class, dependencies = AppComponent.class)
public interface TopRatedComponent {
    void inject(TopRatedFragment fragment);
}
