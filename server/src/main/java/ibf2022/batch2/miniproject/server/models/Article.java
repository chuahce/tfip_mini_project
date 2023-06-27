package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.Serializable;

public class Article implements Serializable {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static Article create(JsonObject jo) {
        Article article = new Article();

        if (jo.containsKey("source")) {
            article.setSource(Source.create(jo.getJsonObject("source")));
        }

        article.setAuthor(jo.getString("author", null));
        article.setTitle(jo.getString("title", null));
        article.setDescription(jo.getString("description", null));
        article.setUrl(jo.getString("url", null));
        article.setUrlToImage(jo.getString("urlToImage", null));
        article.setPublishedAt(jo.getString("publishedAt", null));
        article.setContent(jo.getString("content", null));

        return article;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("source", getSource().toJSON())
                .add("author", getAuthor())
                .add("title", getTitle())
                .add("description", getDescription())
                .add("url", getUrl())
                .add("urlToImage", getUrlToImage())
                .add("publishedAt", getPublishedAt())
                .add("content",
                getContent())
                .build();
    }
}

