import { Component, OnInit } from '@angular/core';
import { MovieScores, MovieSearchResponse, MovieSearchResult } from 'src/app/shared/models/movie-scores';
import { MovieScoresService } from '../../shared/services/movie-scores.service'
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';


@Component({
  selector: 'app-movie-scores',
  templateUrl: './movie-scores.component.html',
  styleUrls: ['./movie-scores.component.css']
})
export class MovieScoresComponent implements OnInit {
  movieResults: MovieScores[] = [];
  search = '';
  imdbIDs = ['tt1630029', 'tt4154796', 'tt0109830', 'tt0190332', 'tt10366206', 'tt6791350', 'tt1375666', 'tt0110357', 'tt1745960', 'tt6710474', 'tt0816692', 'tt2294629', 'tt2096673', 'tt9362722', 'tt3783958', 'tt1345836', 'tt2488496', 'tt2948372', 'tt2948356', 'tt0120338'
  ];

  constructor(private movieScoresService: MovieScoresService, private router: Router) { }

  ngOnInit(): void {
    this.movieScoresService.getMoviesByIDs(this.imdbIDs).subscribe({
      next: movies => {
        this.movieResults = movies;
      },
      error: error => console.error(error) // Log any errors from getMoviesByIDs()
    });
  }

  searchMovies(): void {
    this.movieScoresService.searchMovies(this.search).subscribe({
      next: movies => {
        this.movieResults = movies
          .filter(movie => movie.Poster !== 'N/A' && this.countRatings(movie) >= 1)
          .slice(0, 20); // Limit to the first 20 results
      },
      error: error => console.error(error) // Log any errors from searchMovies()
    });
  }

  // Helper function to count the number of ratings a movie has
  countRatings(movie: MovieScores): number {
    let count = 0;
    for (let rating of movie.Ratings) {
      if (rating.Value !== 'N/A') {
        count++;
      }
    }
    return count;
  }

  goToDetails(movie: MovieScores): void {
    this.movieScoresService.searchMovieOnTMDb(movie.Title).subscribe({
      next: tmdbSearchResults => {
        if (tmdbSearchResults && tmdbSearchResults.length > 0) {
          const correspondingTmdbMovie = tmdbSearchResults[0];
          this.router.navigate(['movie-details', correspondingTmdbMovie.id]);
        } else {
          console.log(`No matching movie found on TMDb for title: ${movie.Title}`);
        }
      },
      error: error => console.error(error)
    });
  }
}

