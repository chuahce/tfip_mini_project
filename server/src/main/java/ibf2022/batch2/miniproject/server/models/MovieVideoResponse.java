package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class MovieVideoResponse implements Serializable {
  private int id;
  private List<MovieVideo> results;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public List<MovieVideo> getResults() {
    return results;
  }

  public void setResults(List<MovieVideo> results) {
    this.results = results;
  }

  public static MovieVideo create(JsonObject jsonObject) {
    MovieVideo movieVideo = new MovieVideo();
    movieVideo.setSite(jsonObject.getString("site", ""));
    movieVideo.setKey(jsonObject.getString("key", ""));
    return movieVideo;
  }

  public JsonObject toJSON() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    getResults().forEach(video -> arrayBuilder.add(video.toJSON()));

    return Json.createObjectBuilder()
        .add("id", getId())
        .add("results", arrayBuilder.build())
        .build();
  }
}
