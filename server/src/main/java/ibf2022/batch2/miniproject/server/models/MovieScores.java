package ibf2022.batch2.miniproject.server.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieScores implements Serializable {

  @JsonProperty("Title")
  private String title;
  @JsonProperty("Year")
  private String year;
  @JsonProperty("Rated")
  private String rated;
  @JsonProperty("Released")
  private String released;
  @JsonProperty("Runtime")
  private String runtime;
  @JsonProperty("Genre")
  private String genre;
  @JsonProperty("Director")
  private String director;
  @JsonProperty("Writer")
  private String writer;
  @JsonProperty("Actors")
  private String actors;
  @JsonProperty("Plot")
  private String plot;
  @JsonProperty("Language")
  private String language;
  @JsonProperty("Country")
  private String country;
  @JsonProperty("Awards")
  private String awards;
  @JsonProperty("Poster")
  private String poster;
  @JsonProperty("Ratings")
  private List<RatingDTO> ratings;
  @JsonProperty("Metascore")
  private String metascore;
  @JsonProperty("imdbRating")
  private String imdbRating;
  @JsonProperty("imdbVotes")
  private String imdbVotes;
  @JsonProperty("imdbID")
  private String imdbID;
  @JsonProperty("Type")
  private String type;
  @JsonProperty("DVD")
  private String DVD;
  @JsonProperty("BoxOffice")
  private String boxOffice;
  @JsonProperty("Production")
  private String production;
  @JsonProperty("Website")
  private String website;
  @JsonProperty("Response")
  private String response;

  // Nested class for ratings
  @Data
  @ToString
  @AllArgsConstructor
  public static class RatingDTO {
    @JsonProperty("Source")
    private String source;
    @JsonProperty("Value")
    private String value;

  }
}
