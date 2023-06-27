package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import java.io.Serializable;

public class Source implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Source create(JsonObject jo) {
        Source source = new Source();
        source.setId(jo.getString("id", null));
        source.setName(jo.getString("name", null));
        return source;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("name", getName())
                .build();
    }
}
