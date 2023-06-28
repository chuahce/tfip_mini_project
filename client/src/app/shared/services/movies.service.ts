import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Movie, MovieCredits, MovieResponse, MovieImages, MovieVideoResponse } from '../models/movie';
import { map, switchMap } from 'rxjs/operators';
import { of } from 'rxjs';
import { GenresResponse } from '../models/genre';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MoviesService {
  baseUrl: string = `${environment.apiUrl}/movies`;

  constructor(private http: HttpClient) { }

  getMovies(type: string = 'upcoming', count: number = 20) {
    return this.http.get<MovieResponse>(`${this.baseUrl}/movie/${type}`).pipe(
      map((res) => {
        const filteredMovies = res.results.filter(movie => movie.backdrop_path && movie.poster_path);
        return filteredMovies.slice(0, count);
      })
    );
  }

  getMovieDetails(id: string) {
    return this.http.get<Movie>(`${this.baseUrl}/${id}`);
  }

  getMovieTrailers(id: string) {
    return this.http
      .get<MovieVideoResponse>(`${this.baseUrl}/${id}/videos`)
      .pipe(
        switchMap((res) => {
          return of(res.results);
        })
      );
  }

  getMoviesGenres() {
    return this.http.get<GenresResponse>(`${this.baseUrl}/genre/movie/list`).pipe(
      switchMap((res) => {
        return of(res.genres);
      })
    );
  }

  getMoviesByGenre(genreId: string, pageNumber: number) {
    return this.http
      .get<MovieResponse>(
        `${this.baseUrl}/discover/movie?with_genres=${genreId}&page=${pageNumber}`
      )
      .pipe(
        map((res) => {
          return res.results.filter(movie => movie.backdrop_path && movie.poster_path);
        })
      );
  }

  getMovieImages(id: string) {
    return this.http.get<MovieImages>(`${this.baseUrl}/${id}/images`);
  }

  getMovieCredits(id: string) {
    return this.http.get<MovieCredits>(
      `${this.baseUrl}/${id}/credits`
    );
  }

  getMovieRecommendations(movieId: string) {
    return this.http.get<MovieResponse>(`${this.baseUrl}/${movieId}/recommendations`).pipe(
      map((res) => {
        return res.results.filter(movie => movie.backdrop_path && movie.poster_path);
      })
    );
  }

  getMovieSimilar(id: string) {
    return this.http
      .get<MovieResponse>(`${this.baseUrl}/${id}/similar`)
      .pipe(
        map((res) => {
          const filteredMovies = res.results.filter(movie => movie.backdrop_path && movie.poster_path);
          return filteredMovies;
        })
      );
  }

  searchMovies(page: number, searchValue?: string) {
    const uri = searchValue ? '/search/movie' : '/movie/popular';
    return this.http
      .get<MovieResponse>(
        `${this.baseUrl}${uri}?page=${page}&query=${searchValue}`
      )
      .pipe(
        map((res) => {
          return res.results.filter(movie => movie.backdrop_path && movie.poster_path);
        })
      );
  }
}

