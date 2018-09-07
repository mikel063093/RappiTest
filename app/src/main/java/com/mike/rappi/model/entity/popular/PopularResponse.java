package com.mike.rappi.model.entity.popular;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by mike on
 */

public class PopularResponse {
    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<PopularResults> popularResultsList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<PopularResults> getPopularResultsList() {
        return popularResultsList;
    }

    public void setPopularResultsList(List<PopularResults> popularResultsList) {
        this.popularResultsList = popularResultsList;
    }
}
