package com.mike.rappi.event;

import com.mike.rappi.model.entity.toprated.TopRatedResults;
import com.mike.rappi.model.entity.upcoming.UpcomingResults;

/**
 * Created by mike
 */

public class UpcomingDetailEvent {
  private UpcomingResults results;

  public UpcomingDetailEvent(UpcomingResults results) {
    this.results = results;
  }

  public UpcomingResults getResults() {
    return results;
  }

  public void setResults(UpcomingResults results) {
    this.results = results;
  }
}
