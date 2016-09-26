package com.example.sungwon.vicenews;

/**
 * Created by SungWon on 9/26/2016.
 */
public class NewsDetail {
    private String title;
    private String preview;
    private String body;
    private String author;
    private String url;
    private String category;
    private String thumb;

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public String getCategory() {
        return category;
    }

    public String getPreview() {
        return preview;
    }

    public String getThumb() {
        return thumb;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "NewsDetail{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", preview='" + preview + '\'' +
                ", body='" + body + '\'' +
                ", url='" + url + '\'' +
                ", category='" + category + '\'' +
                ", thumb='" + thumb + '\'' +
                '}';
    }
}
