package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Genre implements Serializable {
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static Genre create(JsonObject jo) {
    Genre genre = new Genre();
    genre.setId(jo.getInt("id", 0));
    genre.setName(jo.getString("name", ""));
    return genre;
  }

  public JsonObject toJSON() {
    return Json.createObjectBuilder()
        .add("id", getId())
        .add("name", getName())
        .build();
  }
}