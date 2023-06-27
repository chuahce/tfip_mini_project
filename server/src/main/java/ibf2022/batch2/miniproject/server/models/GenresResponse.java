package ibf2022.batch2.miniproject.server.models;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class GenresResponse {
  private List<Genre> genres;

  public List<Genre> getGenres() {
    return genres;
  }
  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public static GenresResponse create(JsonObject jo) {
    GenresResponse genreResponse = new GenresResponse();

    JsonArray genresArray = jo.getJsonArray("genres");
    if (genresArray != null) {
      genreResponse.setGenres(
          genresArray.stream()
              .map(JsonObject.class::cast)
              .map(Genre::create)
              .collect(Collectors.toList()));
    }

    return genreResponse;
  }

  public JsonObject toJSON() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    getGenres().forEach(genre -> arrayBuilder.add(genre.toJSON()));

    return Json.createObjectBuilder()
        .add("genres", arrayBuilder.build())
        .build();
  }
}
