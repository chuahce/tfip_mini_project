package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieSearchResponse implements Serializable {
    private List<MovieSearchResult> Search;
    private String totalResults;
    private String Response;

    public List<MovieSearchResult> getSearch() {
        return Search;
    }

    public void setSearch(List<MovieSearchResult> search) {
        Search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public static MovieSearchResponse create(JsonObject jo) {
        MovieSearchResponse movieSearchResponse = new MovieSearchResponse();

        JsonArray array = jo.getJsonArray("Search");
        List<MovieSearchResult> search = new ArrayList<>();
        for (JsonObject searchResultJson : array.getValuesAs(JsonObject.class)) {
            search.add(MovieSearchResult.create(searchResultJson));
        }
        movieSearchResponse.setSearch(search);

        movieSearchResponse.setTotalResults(jo.getString("totalResults", null));
        movieSearchResponse.setResponse(jo.getString
        ("Response", null));

        return movieSearchResponse;
    }

    public JsonObject toJSON() {
        JsonArrayBuilder searchBuilder = Json.createArrayBuilder();
        for (MovieSearchResult searchResult : Search) {
            searchBuilder.add(searchResult.toJSON());
        }

        return Json.createObjectBuilder()
                .add("Search", searchBuilder.build())
                .add("totalResults", getTotalResults())
                .add("Response", getResponse())
                .build();
    }
}
