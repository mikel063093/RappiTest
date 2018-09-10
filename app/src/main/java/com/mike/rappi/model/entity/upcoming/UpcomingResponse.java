package com.mike.rappi.model.entity.upcoming;

import com.google.gson.annotations.SerializedName;
import com.mike.rappi.model.entity.toprated.TopRatedResults;
import java.util.List;

/**
 * Created by mike
 */

public class UpcomingResponse {

  @SerializedName("page")
  private int page;

  @SerializedName("results")
  private List<UpcomingResults> topRatedResultsList;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<UpcomingResults> getTopRatedResultsList() {
    return topRatedResultsList;
  }

  public void setTopRatedResultsList(
      List<UpcomingResults> topRatedResultsList) {
    this.topRatedResultsList = topRatedResultsList;
  }
}
