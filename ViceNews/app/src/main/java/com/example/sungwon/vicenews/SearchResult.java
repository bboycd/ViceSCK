package com.example.sungwon.vicenews;

/**
 * Created by SungWon on 9/26/2016.
 */

public class SearchResult {
    NewsItem data;

    public NewsItem getData() {
        return data;
    }

    public void setData(NewsItem data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SearchResult{" + data.getItems() +
                '}';
    }
}
