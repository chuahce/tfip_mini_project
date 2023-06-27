import { Injectable } from '@angular/core';
import { MovieScores, MovieSearchResponse } from '../models/movie-scores';
import { Observable, catchError, forkJoin, map, of, switchMap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MovieScoresService {
  // private baseurl = 'http://www.omdbapi.com/';
  private baseurl = 'http://localhost:8080/api/movie-scores';
  private apikey = '89d3ad2d';
  private tmdbBaseurl = 'https://api.themoviedb.org/3';
  private tmdbApiKey = 'ea09862a785b72ce2751bff4bd8f27cb';

  constructor(private http: HttpClient) { }

  getMoviesByIDs(IDs: string[]): Observable<MovieScores[]> {
    return forkJoin(
      IDs.map(imdbID => this.getMovieScores(imdbID))
    ).pipe(
      map(movies => movies.sort((a, b) => {
        const dateA = new Date(a.Released);
        const dateB = new Date(b.Released);
        return dateB.getTime() - dateA.getTime();
      })),
    );
  }

  searchMovies(title: string): Observable<MovieScores[]> {
    return this.http.get<MovieSearchResponse>(`${this.baseurl}/search?searchValue=${title}`).pipe(
      switchMap(response => {
        if (response.Response === 'True') {
          return forkJoin(
            response.Search.map(movieSearchResult =>
              this.getMovieScores(movieSearchResult.imdbID)
            )
          );
        } else {
          return of([]);
        }
      }),
      map(movies => movies.sort((a, b) => {
        const dateA = new Date(a.Released);
        const dateB = new Date(b.Released);
        return dateB.getTime() - dateA.getTime();
      })),
    );
  }

  getMovieScores(imdbID: string): Observable<MovieScores> {
    return this.http.get<MovieScores>(`${this.baseurl}/${imdbID}`);
  }

  searchMovieOnTMDb(title: string): Observable<any> {
    const url = `${this.tmdbBaseurl}/search/movie?api_key=${this.tmdbApiKey}&query=${encodeURIComponent(title)}`;
    return this.http.get<any>(url).pipe(
      map(response => response.results),
      catchError(this.handleError)
    );
  }

  handleError(error: any) {
    return throwError(() => new Error('This is an error'));
  }
}