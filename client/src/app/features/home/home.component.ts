import { Component, OnInit } from '@angular/core';
import { Item } from '../../shared/models/item';
import { MoviesService } from '../../shared/services/movies.service';
import { mapMovieToItem } from '../../shared/utils/item-mapper';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  nowPlayingMovies: Item[] = [];
  upcomingMovies: Item[] = [];
  popularMovies: Item[] = [];
  topRatedMovies: Item[] = [];

  constructor(private moviesService: MoviesService) { }

  ngOnInit(): void {
    this.moviesService.getMovies('popular', 12).subscribe((movies) => {
      this.popularMovies = movies.map((movie) => mapMovieToItem(movie));
    });

    this.moviesService.getMovies('now_playing', 18).subscribe((movies) => {
      this.nowPlayingMovies = movies.map((movie) => mapMovieToItem(movie));
    });

    this.moviesService.getMovies('upcoming', 18).subscribe((movies) => {
      this.upcomingMovies = movies.map((movie) => mapMovieToItem(movie));
    });

    this.moviesService.getMovies('top_rated', 18).subscribe((movies) => {
      this.topRatedMovies = movies.map((movie) => mapMovieToItem(movie));
    });
  }
}

