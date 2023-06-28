import { Injectable } from '@angular/core';
import { MovieScores, MovieSearchResponse } from '../models/movie-scores';
import { Observable, catchError, forkJoin, map, of, switchMap, throwError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class MovieScoresService {
  // private baseurl = 'http://www.omdbapi.com/';
  private baseurl = `${environment.apiUrl}/movie-scores`;

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

  handleError(error: any) {
    return throwError(() => new Error('This is an error'));
  }
}
