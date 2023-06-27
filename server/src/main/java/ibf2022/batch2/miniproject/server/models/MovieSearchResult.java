package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.Serializable;

public class MovieSearchResult implements Serializable {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public static MovieSearchResult create(JsonObject jo) {
        MovieSearchResult movieSearchResult = new MovieSearchResult();

        movieSearchResult.setTitle(jo.getString("Title", null));
        movieSearchResult.setYear(jo.getString("Year", null));
        movieSearchResult.setImdbID(jo.getString("imdbID", null));
        movieSearchResult.setType(jo.getString("Type", null));
        movieSearchResult.setPoster(jo.getString("Poster", null));

        return movieSearchResult;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("Title", getTitle())
                .add("Year", getYear())
                .add("imdbID", getImdbID())
                .add("Type", getType())
                .add("Poster", getPoster())
                .build();
    }
}
