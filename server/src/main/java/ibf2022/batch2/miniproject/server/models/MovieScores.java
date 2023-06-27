package ibf2022.batch2.miniproject.server.models;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieScores implements Serializable {

    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private List<Rating> Ratings;
    private String Metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String Type;
    private String DVD;
    private String BoxOffice;
    private String Production;
    private String Website;
    private String Response;

    

    public String getTitle() {
      return Title;
    }

    public void setTitle(String title) {
      Title = title;
    }

    public String getYear() {
      return Year;
    }

    public void setYear(String year) {
      Year = year;
    }

    public String getRated() {
      return Rated;
    }

    public void setRated(String rated) {
      Rated = rated;
    }

    public String getReleased() {
      return Released;
    }

    public void setReleased(String released) {
      Released = released;
    }

    public String getRuntime() {
      return Runtime;
    }

    public void setRuntime(String runtime) {
      Runtime = runtime;
    }

    public String getGenre() {
      return Genre;
    }

    public void setGenre(String genre) {
      Genre = genre;
    }

    public String getDirector() {
      return Director;
    }

    public void setDirector(String director) {
      Director = director;
    }

    public String getWriter() {
      return Writer;
    }

    public void setWriter(String writer) {
      Writer = writer;
    }

    public String getActors() {
      return Actors;
    }

    public void setActors(String actors) {
      Actors = actors;
    }

    public String getPlot() {
      return Plot;
    }

    public void setPlot(String plot) {
      Plot = plot;
    }

    public String getLanguage() {
      return Language;
    }

    public void setLanguage(String language) {
      Language = language;
    }

    public String getCountry() {
      return Country;
    }

    public void setCountry(String country) {
      Country = country;
    }

    public String getAwards() {
      return Awards;
    }

    public void setAwards(String awards) {
      Awards = awards;
    }

    public String getPoster() {
      return Poster;
    }

    public void setPoster(String poster) {
      Poster = poster;
    }

    public List<Rating> getRatings() {
      return Ratings;
    }

    public void setRatings(List<Rating> ratings) {
      Ratings = ratings;
    }

    public String getMetascore() {
      return Metascore;
    }

    public void setMetascore(String metascore) {
      Metascore = metascore;
    }

    public String getImdbRating() {
      return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
      this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
      return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
      this.imdbVotes = imdbVotes;
    }

    public String getImdbID() {
      return imdbID;
    }

    public void setImdbID(String imdbID) {
      this.imdbID = imdbID;
    }

    public String getType() {
      return Type;
    }

    public void setType(String type) {
      Type = type;
    }

    public String getDVD() {
      return DVD;
    }

    public void setDVD(String dVD) {
      DVD = dVD;
    }

    public String getBoxOffice() {
      return BoxOffice;
    }

    public void setBoxOffice(String boxOffice) {
      BoxOffice = boxOffice;
    }

    public String getProduction() {
      return Production;
    }

    public void setProduction(String production) {
      Production = production;
    }

    public String getWebsite() {
      return Website;
    }

    public void setWebsite(String website) {
      Website = website;
    }

    public String getResponse() {
      return Response;
    }

    public void setResponse(String response) {
      Response = response;
    }

    public static MovieScores create(JsonObject jo) {
        MovieScores movieScores = new MovieScores();

        movieScores.setTitle(jo.getString("Title", null));
        movieScores.setYear(jo.getString("Year", null));
        movieScores.setRated(jo.getString("Rated", null));
        movieScores.setReleased(jo.getString("Released", null));
        movieScores.setRuntime(jo.getString("Runtime", null));
        movieScores.setGenre(jo.getString("Genre", null));
        movieScores.setDirector(jo.getString("Director", null));
        movieScores.setWriter(jo.getString("Writer", null));
        movieScores.setActors(jo.getString("Actors", null));
        movieScores.setPlot(jo.getString("Plot", null));
        movieScores.setLanguage(jo.getString("Language", null));
        movieScores.setCountry(jo.getString("Country", null));
        movieScores.setAwards(jo.getString("Awards", null));
        movieScores.setPoster(jo.getString("Poster", null));
        movieScores.setMetascore(jo.getString("Metascore", null));
        movieScores.setImdbRating(jo.getString("imdbRating", null));
        movieScores.setImdbVotes(jo.getString("imdbVotes", null));
        movieScores.setImdbID(jo.getString("imdbID", null));
        movieScores.setType(jo.getString("Type", null));
        movieScores.setDVD(jo.getString("DVD", null));
        movieScores.setBoxOffice(jo.getString("BoxOffice", null));
        movieScores.setProduction(jo.getString("Production", null));
        movieScores.setWebsite(jo.getString("Website", null));
        movieScores.setResponse(jo.getString("Response", null));

                JsonArray array = jo.getJsonArray("Ratings");
        List<Rating> ratings = new ArrayList<>();
        for (JsonValue value : array) {
            ratings.add(Rating.create(value.asJsonObject()));
        }
        movieScores.setRatings(ratings);

        return movieScores;
    }

    public JsonObject toJSON() {
        var ratingsBuilder = Json.createArrayBuilder();
        for (Rating rating : Ratings) {
            ratingsBuilder.add(rating.toJSON());
        }

        return Json.createObjectBuilder()
            .add("Title", getTitle())
            .add("Year", getYear())
            .add("Rated", getRated())
            .add("Released", getReleased())
            .add("Runtime", getRuntime())
            .add("Genre", getGenre())
            .add("Director", getDirector())
            .add("Writer", getWriter())
            .add("Actors", getActors())
            .add("Plot", getPlot())
            .add("Language", getLanguage())
            .add("Country", getCountry())
            .add("Awards", getAwards())
            .add("Poster", getPoster())
            .add("Ratings", ratingsBuilder.build())
            .add("Metascore", getMetascore())
            .add("imdbRating", getImdbRating())
            .add("imdbVotes", getImdbVotes())
            .add("imdbID", getImdbID())
            .add("Type", getType())
            .add("DVD", getDVD())
            .add("BoxOffice", getBoxOffice())
            .add("Production", getProduction())
            .add("Website", getWebsite())
            .add("Response", getResponse())
            .build();
    }

   
}
