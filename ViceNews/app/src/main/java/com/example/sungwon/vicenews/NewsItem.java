package com.example.sungwon.vicenews;

/**
 * Created by SungWon on 9/26/2016.
 */
public class NewsItem {

    NewsDetail[] items;

    public NewsDetail[] getItems() {
        return items;
    }

    public void setItems(NewsDetail[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "NewsItem{" +
                "items=" + items[0].getTitle() +
                "\n" + items[0].getPreview() +
                "\n" + items[0].getBody() +
                "\n" + items[0].getAuthor() +
                "\n" + items[0].getUrl() +
                "\n" + items[0].getCategory() +
                "\n" + items[0].getThumb();
    }
}
