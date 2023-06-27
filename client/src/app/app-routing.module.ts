import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HomeComponent } from './features/home/home.component';
import { MoviesComponent } from './features/movies/movies.component';
import { MovieDetailsComponent } from './features/movie-details/movie-details.component';
import { GenresComponent } from './features/genres/genres.component';
import { MovieNewsComponent } from './features/movie-news/movie-news.component';
import { MovieScoresComponent } from './features/movie-scores/movie-scores.component';
import { AuthComponent } from './features/auth/auth.component';
import { WatchListMoviesComponent } from './features/watchlist/watchlist-movies/watchlist-movies.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'movies', component: MoviesComponent },
  { path: 'movies/genres/:genreId', component: MoviesComponent },
  { path: 'movie-details/:id', component: MovieDetailsComponent },
  { path: 'movie-news', component: MovieNewsComponent },
  { path: 'movie-scores', component: MovieScoresComponent },
  { path: 'genres', component: GenresComponent },
  { path: 'movie-details/:title', component: MovieDetailsComponent },
  { path: 'auth/signin', component: AuthComponent },
  { path: 'auth/signup', component: AuthComponent },
  { path: 'auth/signout', component: AuthComponent },
  { path: 'watchlist', component: WatchListMoviesComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
