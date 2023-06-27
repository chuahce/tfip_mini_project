import { Component, OnInit } from '@angular/core';
import { Genre } from '../../shared/models/genre';
import { MoviesService } from '../../shared/services/movies.service';
// import { TvShowsService } from '../../shared/services/tvshows.service';

@Component({
  selector: 'app-genres',
  templateUrl: './genres.component.html',
  styleUrls: ['./genres.component.css']
})
export class GenresComponent implements OnInit {
  genres: Genre[] = [];
  tvShowGenres: Genre[] = [];
  constructor(private moviesService: MoviesService,
  ) { }

  ngOnInit(): void {
    this.moviesService.getMoviesGenres().subscribe((genresData) => {
      this.genres = genresData;
    });
  }
}