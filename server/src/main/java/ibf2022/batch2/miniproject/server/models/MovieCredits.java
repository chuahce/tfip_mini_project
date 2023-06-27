package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class MovieCredits implements Serializable {

    private List<CastMember> cast;
    private List<CrewMember> crew;

    public List<CastMember> getCast() {
        return cast;
    }

    public void setCast(List<CastMember> cast) {
        this.cast = cast;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public static MovieCredits create(JsonObject jo) {
        MovieCredits movieCredits = new MovieCredits();

        JsonArray castArray = jo.getJsonArray("cast");
        if (castArray != null) {
            movieCredits.setCast(
                    castArray.stream()
                            .map(JsonObject.class::cast)
                            .map(CastMember::create)
                            .collect(Collectors.toList()));
        }

        JsonArray crewArray = jo.getJsonArray("crew");
        if (crewArray != null) {
            movieCredits.setCrew(
                    crewArray.stream()
                            .map(JsonObject.class::cast)
                            .map(CrewMember::create)
                            .collect(Collectors.toList()));
        }

        return movieCredits;
    }

    public JsonObject toJSON() {
        JsonArrayBuilder castArrayBuilder = Json.createArrayBuilder();
        for (CastMember c : getCast()) {
            castArrayBuilder.add(c.toJSON());
        }

        JsonArrayBuilder crewArrayBuilder = Json.createArrayBuilder();
        for (CrewMember c : getCrew()) {
            crewArrayBuilder.add(c.toJSON());
        }

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("cast", castArrayBuilder.build())
                .add("crew", crewArrayBuilder.build());

        return builder.build();
    }
}
