package ibf2022.batch2.miniproject.server.controllers;

import ibf2022.batch2.miniproject.server.models.MovieScores;
import ibf2022.batch2.miniproject.server.models.MovieSearchResponse;
import ibf2022.batch2.miniproject.server.services.MovieScoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie-scores")
@CrossOrigin(origins = "*")
public class MovieScoresController {

    @Autowired
    private MovieScoresService movieScoresService;

    @PostMapping("/moviesById")
    public ResponseEntity<List<MovieScores>> getMoviesByIDs(@RequestBody List<String> IDs) {
        List<MovieScores> movieScores = movieScoresService.getMoviesByIDs(IDs);
        return new ResponseEntity<>(movieScores, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<MovieSearchResponse> searchMovies(@RequestParam("searchValue") String searchValue) {
        return new ResponseEntity<>(movieScoresService.searchMovies(searchValue), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieScores> getMovieScores(@PathVariable String id) {
        MovieScores movieScores = movieScoresService.getMovieScores(id);
        return new ResponseEntity<>(movieScores, HttpStatus.OK);
    }
}
