package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class MovieVideo implements Serializable {
  private String site;
  private String key;

  public String getSite() {
    return site;
  }

  public void setSite(String site) {
    this.site = site;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public static MovieVideo create(JsonObject jo) {
    MovieVideo video = new MovieVideo();

    video.setSite(jo.getString("site", ""));
    video.setKey(jo.getString("key", ""));

    return video;
  }

  public JsonObject toJSON() {
    return Json.createObjectBuilder()
        .add("site", getSite())
        .add("key", getKey())
        .build();
  }
}
