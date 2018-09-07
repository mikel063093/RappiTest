package com.mike.rappi.event;

import com.mike.rappi.model.entity.toprated.TopRatedResults;

/**
 * Created by mike
 */

public class TopRatedDetailEvent {
  private TopRatedResults results;

  public TopRatedDetailEvent(TopRatedResults results) {
    this.results = results;
  }

  public TopRatedResults getResults() {
    return results;
  }

  public void setResults(TopRatedResults results) {
    this.results = results;
  }
}
