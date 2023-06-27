package ibf2022.batch2.miniproject.server.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class Movie implements Serializable {
    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private int id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;
    private int revenue;
    private int runtime;
    private String status;
    private List<Genre> genres;

    

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public static Movie create(JsonObject jo) {
        Movie movie = new Movie();

        movie.setAdult(jo.getBoolean("adult", false));
        movie.setBackdrop_path(jo.getString("backdrop_path", null));

        // Manually handling genre_ids as a list of integers
        JsonArray array = jo.getJsonArray("genre_ids");
        List<Integer> genre_ids = new ArrayList<>();
        for (JsonValue value : array) {
            genre_ids.add(Integer.parseInt(value.toString()));
        }
        movie.setGenre_ids(genre_ids);

        movie.setId(jo.getInt("id", 0));
        movie.setOriginal_language(jo.getString("original_language", null));
        movie.setOriginal_title(jo.getString("original_title", null));
        movie.setOverview(jo.getString("overview", null));
        movie.setPopularity(jo.getJsonNumber("popularity").doubleValue());
        movie.setPoster_path(jo.getString("poster_path", null));
        movie.setRelease_date(jo.getString("release_date", null));
        movie.setTitle(jo.getString("title", null));
        movie.setVideo(jo.getBoolean("video", false));
        movie.setVote_average(jo.getJsonNumber("vote_average").doubleValue());
        movie.setVote_count(jo.getInt("vote_count", 0));

        if (jo.containsKey("revenue"))
            movie.setRevenue(jo.getInt("revenue"));

        if (jo.containsKey("runtime"))
            movie.setRuntime(jo.getInt("runtime"));

        if (jo.containsKey("status"))
            movie.setStatus(jo.getString("status"));

        if (jo.containsKey("genres")) {
            JsonArray genresArray = jo.getJsonArray("genres");
            List<Genre> genres = new ArrayList<>();
            for (JsonValue value : genresArray) {
                genres.add(Genre.create(value.asJsonObject()));
            }
            movie.setGenres(genres);
        }

        return movie;
    }

    public JsonObject toJSON() {
    var genreIdsBuilder = Json.createArrayBuilder();
    for (Integer genreId : genre_ids) {
        genreIdsBuilder.add(genreId);
    }

        var genresBuilder = Json.createArrayBuilder();
        for (Genre genre : genres) {
            genresBuilder.add(genre.toJSON());
        }

        return Json.createObjectBuilder()
            .add("adult", isAdult())
            .add("backdrop_path", getBackdrop_path())
            .add("genre_ids", genreIdsBuilder.build())
            .add("id", getId())
            .add("original_language", getOriginal_language())
            .add("original_title", getOriginal_title())
            .add("overview", getOverview())
            .add("popularity", getPopularity())
            .add("poster_path", getPoster_path())
            .add("release_date", getRelease_date())
            .add("title", getTitle())
            .add("video", isVideo())
            .add("vote_average", getVote_average())
            .add("vote_count", getVote_count())
            .add("revenue", getRevenue())
            .add("runtime", getRuntime())
            .add("status", getStatus())
            .add("genres", genresBuilder.build())
            .build();
    }
}




                




    

    
