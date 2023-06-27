package ibf2022.batch2.miniproject.server.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.miniproject.server.models.GenresResponse;
import ibf2022.batch2.miniproject.server.models.Movie;
import ibf2022.batch2.miniproject.server.models.MovieCredits;
import ibf2022.batch2.miniproject.server.models.MovieImages;
import ibf2022.batch2.miniproject.server.models.MovieResponse;
import ibf2022.batch2.miniproject.server.models.MovieVideoResponse;
import ibf2022.batch2.miniproject.server.services.MoviesService;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin(origins = "*")
public class MoviesController {

  @Autowired
  private MoviesService moviesService;

  @GetMapping("/movie/{type}")
  public ResponseEntity<MovieResponse> getMovies(@PathVariable("type") String type,
      @RequestParam(defaultValue = "1") int page) {
    MovieResponse movies = moviesService.getMovies(type, page);
    return new ResponseEntity<>(movies, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Movie> getMovieDetails(@PathVariable String id) {
    return new ResponseEntity<>(moviesService.getMovieDetails(id), HttpStatus.OK);
  }

  @GetMapping("/{id}/videos")
  public ResponseEntity<MovieVideoResponse> getMovieTrailers(@PathVariable String id) {
    return new ResponseEntity<>(moviesService.getMovieTrailers(id), HttpStatus.OK);
  }

  @GetMapping("/genre/movie/list")
  public ResponseEntity<GenresResponse> getMoviesGenres() {
    return new ResponseEntity<>(moviesService.getMoviesGenres(), HttpStatus.OK);
  }

  @GetMapping("/discover/movie")
  public ResponseEntity<MovieResponse> getMoviesByGenre(@RequestParam String genreId, @RequestParam int pageNumber) {
    return new ResponseEntity<>(moviesService.getMoviesByGenre(genreId, pageNumber), HttpStatus.OK);
  }

  @GetMapping("/{id}/images")
  public ResponseEntity<MovieImages> getMovieImages(@PathVariable String id) {
    return new ResponseEntity<>(moviesService.getMovieImages(id), HttpStatus.OK);
  }

  @GetMapping("/{id}/credits")
  public ResponseEntity<MovieCredits> getMovieCredits(@PathVariable String id) {
    return new ResponseEntity<>(moviesService.getMovieCredits(id), HttpStatus.OK);
  }

  @GetMapping("/{id}/similar")
  public ResponseEntity<MovieResponse> getMovieSimilar(@PathVariable String id) {
    return new ResponseEntity<>(moviesService.getMovieSimilar(id), HttpStatus.OK);
  }

  @GetMapping("/{id}/recommendations")
  public ResponseEntity<MovieResponse> getMovieRecommendations(@PathVariable String id) {
    return new ResponseEntity<>(moviesService.getMovieRecommendations(id), HttpStatus.OK);
  }

  @GetMapping("/search/movie")
  public ResponseEntity<MovieResponse> searchMovies(@RequestParam int page, @RequestParam String query) {
    return new ResponseEntity<>(moviesService.searchMovies(page, query), HttpStatus.OK);
  }
}