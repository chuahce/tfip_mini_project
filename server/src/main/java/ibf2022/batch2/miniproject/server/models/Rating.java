package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.io.Serializable;

public class Rating implements Serializable {
    private String Source;
    private String Value;

    public Rating() {
    }

    public Rating(String Source, String Value) {
        this.Source = Source;
        this.Value = Value;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String Source) {
        this.Source = Source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String Value) {
        this.Value = Value;
    }

    public static Rating create(JsonObject jo) {
        Rating rating = new Rating();

        rating.setSource(jo.getString("Source", null));
        rating.setValue(jo.getString("Value", null));

        return rating;
    }

    public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("Source", getSource())
                .add("Value", getValue())
                .build();
    }
}
