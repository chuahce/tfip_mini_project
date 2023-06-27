package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class MovieImages implements Serializable {
  public static class Backdrop implements Serializable {
    private String file_path;

    public String getFile_path() {
      return file_path;
    }

    public void setFile_path(String file_path) {
      this.file_path = file_path;
    }

    public static Backdrop create(JsonObject jo) {
      Backdrop backdrop = new Backdrop();
      backdrop.setFile_path(jo.getString("file_path", ""));
      return backdrop;
    }

    public JsonObject toJSON() {
      return Json.createObjectBuilder()
          .add("file_path", getFile_path())
          .build();
    }
  }

  private List<Backdrop> backdrops;

  public List<Backdrop> getBackdrops() { return backdrops; }
  public void setBackdrops(List<Backdrop> backdrops) { this.backdrops = backdrops; }

  public static MovieImages create(JsonObject jo) {
    MovieImages movieImages = new MovieImages();

    JsonArray backdropsArray = jo.getJsonArray("backdrops");
    if (backdropsArray != null) {
      movieImages.setBackdrops(
          backdropsArray.stream()
              .map(JsonObject.class::cast)
              .map(Backdrop::create)
              .collect(Collectors.toList()));
    }

    return movieImages;
  }

  public JsonObject toJSON() {
    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
    getBackdrops().forEach(backdrop -> arrayBuilder.add(backdrop.toJSON()));

    return Json.createObjectBuilder()
        .add("backdrops", arrayBuilder.build())
        .build();
  }
}