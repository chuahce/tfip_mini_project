package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class CastMember implements Serializable {

  private String name;
  private String profile_path;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProfile_path() {
    return profile_path;
  }

  public void setProfile_path(String profile_path) {
    this.profile_path = profile_path;
  }

  public static CastMember create(JsonObject jo) {
    CastMember castMember = new CastMember();

    castMember.setName(jo.getString("name", ""));
    castMember.setProfile_path(jo.getString("profile_path", ""));

    return castMember;
  }

  public JsonObject toJSON() {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    builder.add("name", getName())
        .add("profile_path", getProfile_path());
    return builder.build();
  }
}