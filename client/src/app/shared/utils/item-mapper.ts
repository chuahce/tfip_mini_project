import { Item } from "../models/item";
import { Movie } from "../models/movie";

export const mapMovieToItem = (movie: Movie): Item => {
  return {
    id: movie.id,
    original_title: movie.original_title,
    poster_path: movie.poster_path,
    vote_average: movie.vote_average,
    backdrop_path: movie.backdrop_path,
    vote_count: movie.vote_count,
    release_date: movie.release_date,
    overview: movie.overview,
    runtime: movie.runtime,
    genre_ids: movie.genre_ids,
    genres: movie.genres,
    routePath: '/movie/' + movie.id
  };
};