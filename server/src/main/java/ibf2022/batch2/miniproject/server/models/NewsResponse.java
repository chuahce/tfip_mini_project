package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NewsResponse implements Serializable {
    private String status;
    private int totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public static NewsResponse create(JsonObject jo) {
        NewsResponse newsResponse = new NewsResponse();

        newsResponse.setStatus(jo.getString("status", null));
        newsResponse.setTotalResults(jo.getInt("totalResults", 0));

    
        if (jo.containsKey("articles")) {
            JsonArray articlesArray = jo.getJsonArray("articles");
            List<Article> articles = new ArrayList<>();
            for (JsonValue value : articlesArray) {
                articles.add(Article.create(value.asJsonObject()));
            }
            newsResponse.setArticles(articles);
        }

        return newsResponse;
    }

    public JsonObject toJSON() {
        
        var articlesBuilder = Json.createArrayBuilder();
        for (Article article : articles) {
            articlesBuilder.add(article.toJSON());
        }

        return Json.createObjectBuilder()
                .add("status", getStatus())
                .add("totalResults", getTotalResults())
                .add("articles", articlesBuilder.build())
                .build();
    }
}
