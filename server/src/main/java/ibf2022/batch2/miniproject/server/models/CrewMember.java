package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class CrewMember implements Serializable {

  private String name;
  private String job;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public static CrewMember create(JsonObject jo) {
    CrewMember crewMember = new CrewMember();

    crewMember.setName(jo.getString("name", ""));
    crewMember.setJob(jo.getString("job", ""));

    return crewMember;
  }

  public JsonObject toJSON() {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    builder.add("name", getName())
        .add("job", getJob());
    return builder.build();
  }
}
