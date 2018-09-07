package com.mike.rappi.model.entity.toprated;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mike
 */

public class TopRatedResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<TopRatedResults> topRatedResultsList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<TopRatedResults> getTopRatedResultsList() {
        return topRatedResultsList;
    }

    public void setTopRatedResultsList(List<TopRatedResults> topRatedResultsList) {
        this.topRatedResultsList = topRatedResultsList;
    }
}
