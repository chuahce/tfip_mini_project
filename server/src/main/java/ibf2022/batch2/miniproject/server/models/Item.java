package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Item implements Serializable {
  private int id;
  private String poster_path;
  private String title;
  private double vote_average;
  private String backdrop_path;
  private int vote_count;
  private String release_date;
  private String overview;
  private String routePath;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPoster_path() {
    return poster_path;
  }

  public void setPoster_path(String poster_path) {
    this.poster_path = poster_path;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public double getVote_average() {
    return vote_average;
  }

  public void setVote_average(double vote_average) {
    this.vote_average = vote_average;
  }

  public String getBackdrop_path() {
    return backdrop_path;
  }

  public void setBackdrop_path(String backdrop_path) {
    this.backdrop_path = backdrop_path;
  }

  public int getVote_count() {
    return vote_count;
  }

  public void setVote_count(int vote_count) {
    this.vote_count = vote_count;
  }

  public String getRelease_date() {
    return release_date;
  }

  public void setRelease_date(String release_date) {
    this.release_date = release_date;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getRoutePath() {
    return routePath;
  }

  public void setRoutePath(String routePath) {
    this.routePath = routePath;
  }

  public static Item create(JsonObject jo) {
    Item item = new Item();

    item.setId(jo.getInt("id", 0));
    item.setPoster_path(jo.getString("poster_path", ""));
    item.setTitle(jo.getString("title", ""));
    item.setVote_average(jo.getJsonNumber("vote_average").doubleValue());
    item.setBackdrop_path(jo.getString("backdrop_path", ""));
    item.setVote_count(jo.getInt("vote_count", 0));
    item.setRelease_date(jo.getString("release_date", ""));
    item.setOverview(jo.getString("overview", ""));
    if (jo.containsKey("routePath")) {
      item.setRoutePath(jo.getString("routePath", ""));
    }

    return item;
  }

  public JsonObject toJSON() {
    JsonObjectBuilder builder = Json.createObjectBuilder();

    builder.add("id", getId())
        .add("poster_path", getPoster_path())
        .add("title", getTitle())
        .add("vote_average", getVote_average())
        .add("backdrop_path", getBackdrop_path())
        .add("vote_count", getVote_count())
        .add("release_date", getRelease_date())
        .add("overview", getOverview());

    if (getRoutePath() != null) {
      builder.add("routePath", getRoutePath());
    }

    return builder.build();
  }
}