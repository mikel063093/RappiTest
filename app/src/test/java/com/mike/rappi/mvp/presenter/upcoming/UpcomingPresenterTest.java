package com.mike.rappi.mvp.presenter.upcoming;

import com.mike.rappi.model.api.ApiSource;
import com.mike.rappi.model.entity.upcoming.UpcomingResponse;
import com.mike.rappi.model.entity.upcoming.UpcomingResults;
import com.mike.rappi.mvp.view.upcoming.IUpcomingView;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.rule.PowerMockRule;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static rx.Observable.just;




public class UpcomingPresenterTest {
  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @Mock IUpcomingView view;
  @Mock ApiSource apiSource;
  // @Mock Realm realm;
  @Mock UpcomingPresenter upcomingPresenter;

  @Before
  public void setUp() {

    // Realm.init(InstrumentationRegistry.getInstrumentation().getTargetContext());;

    when(Realm.getDefaultInstance()).thenReturn(UpcomingPresenterTest.mockRealm());
    //this.realm = testRealm;
    //upcomingPresenter = new UpcomingPresenter(view, apiSource);
  }


  public void testLoadUpcomingMovies() throws Exception {
    UpcomingResponse upcomingResponse = new UpcomingResponse();
    UpcomingResults upcomingResults = new UpcomingResults();
    upcomingResults.setId(213123);
    List<UpcomingResults> list = new ArrayList<>();
    list.add(upcomingResults);
    upcomingResponse.setTopRatedResultsList(list);

    when(apiSource.getUpcomingMovies(anyString(), anyString())).thenReturn(just(upcomingResponse));

    upcomingPresenter.loadUpcomingMovies();
  }

  private static Realm mockRealm() {
    mockStatic(Realm.class);

    Realm mockRealm = PowerMockito.mock(Realm.class);

    // when(mockRealm.createObject(Address.class)).thenReturn(new Address());

    when(Realm.getDefaultInstance()).thenReturn(mockRealm);

    return mockRealm;
  }
}
