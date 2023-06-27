import { Genre } from "./movie";

export interface Item {
  id: number;
  poster_path: string;
  original_title: string;
  vote_average: number;
  backdrop_path: string;
  vote_count: number;
  release_date: string;
  overview: string;
  runtime: number;
  genre_ids: number[];
  genres: Genre[];
  routePath?: string;
}