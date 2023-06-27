package ibf2022.batch2.miniproject.server.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ibf2022.batch2.miniproject.server.models.GenresResponse;
import ibf2022.batch2.miniproject.server.models.Movie;
import ibf2022.batch2.miniproject.server.models.MovieCredits;
import ibf2022.batch2.miniproject.server.models.MovieImages;
import ibf2022.batch2.miniproject.server.models.MovieResponse;
import ibf2022.batch2.miniproject.server.models.MovieVideoResponse;

@Service
public class MoviesService {
  public static final String API_URL = "https://api.themoviedb.org/3";
  // public static final String IMAGE_API_URL = "https://api.themoviedb.org/3/movie";
  public static final String IMAGE_API_URL = "https://image.tmdb.org/t/p/w342";

  @Value("${tmdb.api.key}")
  private String apiKey;

  private final RestTemplate restTemplate = new RestTemplate();

  public MovieResponse getMovies(String type, int page) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
            .pathSegment("movie", type)
            .queryParam("api_key", apiKey)
            .queryParam("page", page)
        .toUriString();
    return getEntity(url, MovieResponse.class).getBody();
  }

  public Movie getMovieDetails(String id) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
        .pathSegment("movie", id)
        .queryParam("api_key", apiKey)
        .toUriString();
    return getEntity(url, Movie.class).getBody();
  }

  public MovieVideoResponse getMovieTrailers(String id) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
        .pathSegment("movie", id, "videos")
        .queryParam("api_key", apiKey)
        .toUriString();
    return getEntity(url, MovieVideoResponse.class).getBody();
  }

  public GenresResponse getMoviesGenres() {
    String url = UriComponentsBuilder.fromUriString(API_URL)
        .pathSegment("genre", "movie", "list")
        .queryParam("api_key", apiKey)
        .toUriString();
    return getEntity(url, GenresResponse.class).getBody();
  }

  public MovieResponse getMoviesByGenre(String genreId, int pageNumber) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
        .pathSegment("discover", "movie")
        .queryParam("with_genres", genreId)
        .queryParam("page", pageNumber)
        .queryParam("api_key", apiKey)
        .toUriString();
    return getEntity(url, MovieResponse.class).getBody();
  }

  public MovieImages getMovieImages(String id) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
        .pathSegment("movie", id, "images")
        .queryParam("api_key", apiKey)
        .toUriString();
    return getEntity(url, MovieImages.class).getBody();
  }

  public MovieCredits getMovieCredits(String id) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
        .pathSegment("movie", id, "credits")
        .queryParam("api_key", apiKey)
        .toUriString();
    return getEntity(url, MovieCredits.class).getBody();
  }

  public MovieResponse getMovieSimilar(String id) {
        String url = UriComponentsBuilder.fromUriString(API_URL)
                .pathSegment("movie", id, "similar")
                .queryParam("api_key", apiKey)
                .toUriString();
        return getEntity(url, MovieResponse.class).getBody();
  }

  public MovieResponse getMovieRecommendations(String id) {
    String url = UriComponentsBuilder.fromUriString(API_URL)
            .pathSegment("movie", id, "recommendations")
            .queryParam("api_key", apiKey)
            .toUriString();
    return getEntity(url, MovieResponse.class).getBody();
  }

  public MovieResponse searchMovies(int page, String searchValue) {
    String uri = searchValue != null ? "/search/movie" : "movie/popular";
    String url = UriComponentsBuilder.fromUriString(API_URL)
            .path(uri)
            .queryParam("page", page)
            .queryParam("query", searchValue)
            .queryParam("api_key", apiKey)
            .toUriString();
    return getEntity(url, MovieResponse.class).getBody();
  }

  private <T> ResponseEntity<T> getEntity(String url, Class<T> responseType) {
      return restTemplate.getForEntity(url, responseType);
  }
}