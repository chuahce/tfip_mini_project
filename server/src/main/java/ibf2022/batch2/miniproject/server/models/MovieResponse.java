package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class MovieResponse implements Serializable {
  private int page;
  private List<Movie> results;
  private int total_results;
  private int total_pages;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<Movie> getResults() {
    return results;
  }

  public void setResults(List<Movie> results) {
    this.results = results;
  }

  public int getTotal_results() {
    return total_results;
  }

  public void setTotal_results(int total_results) {
    this.total_results = total_results;
  }

  public int getTotal_pages() {
    return total_pages;
  }

  public void setTotal_pages(int total_pages) {
    this.total_pages = total_pages;
  }

  public static MovieResponse create(JsonObject jo) {
    MovieResponse movieResponse = new MovieResponse();

    movieResponse.setPage(jo.getInt("page", 0));

    JsonArray resultsArray = jo.getJsonArray("results");
    if (resultsArray != null) {
      movieResponse.setResults(
          resultsArray.stream()
              .map(JsonObject.class::cast)
              .map(Movie::create)
              .collect(Collectors.toList()));
    }

    movieResponse.setTotal_results(jo.getInt("total_results", 0));
    movieResponse.setTotal_pages(jo.getInt("total_pages", 0));

    return movieResponse;
  }

  public JsonObject toJSON() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    getResults().forEach(movie -> arrayBuilder.add(movie.toJSON()));

    return Json.createObjectBuilder()
        .add("page", getPage())
        .add("results", arrayBuilder.build())
        .add("total_results", getTotal_results())
        .add("total_pages", getTotal_pages())
        .build();
  }
}
