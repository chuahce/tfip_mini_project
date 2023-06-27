import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IMAGES_SIZES } from '../../shared/constants/images-sizes';
import { CrewMember, Movie, MovieCredits, MovieImages, MovieVideo } from '../../shared/models/movie';
import { MoviesService } from '../../shared/services/movies.service';
import { faPlus } from '@fortawesome/free-solid-svg-icons';
import { StorageService } from 'src/app/shared/services/storage.service';

@Component({
  selector: 'app-movie-details',
  templateUrl: './movie-details.component.html',
  styleUrls: ['./movie-details.component.css']
})
export class MovieDetailsComponent implements OnInit, OnDestroy {
  movie: Movie | null = null;
  movieTrailers: MovieVideo[] = [];
  movieImages: MovieImages | null = null;
  movieCredits: MovieCredits | null = null;
  imagesSizes = IMAGES_SIZES;
  recommendations: Movie[] = [];
  similarMovies: Movie[] = [];
  directors: string[] = [];
  languages: { [key: string]: string } = {
    'en': 'English',
    'zh': 'Chinese',
    'ja': 'Japanese',
    'ko': 'Korean',
  };

  faPlus = faPlus;
  isLoggedIn = false;

  constructor(private route: ActivatedRoute, private moviesService: MoviesService, private storage: StorageService) {
    this.isLoggedIn = this.storage.loggedIn()
  }

  ngOnInit(): void {
    this.route.params.pipe().subscribe(({ id }) => {
      this.getMovieDetails(id);
      this.getMovieTrailers(id);
      this.getMovieImages(id);
      this.getMovieCredits(id);
      this.getMovieRecommendations(id);
      this.getMovieSimilar(id);
    });
  }

  ngOnDestroy() {
    console.log('component destroyed');
  }

  getMovieDetails(id: string) {
    this.moviesService.getMovieDetails(id).subscribe((movieData) => {
      this.movie = movieData;
    });
  }

  getMovieTrailers(id: string) {
    this.moviesService.getMovieTrailers(id).subscribe((movieTrailersData) => {
      this.movieTrailers = movieTrailersData;
    });
  }

  getMovieRecommendations(id: string) {
    this.moviesService.getMovieRecommendations(id).subscribe((recommendationsData) => {
      this.recommendations = recommendationsData.slice(0, 18);
    });
  }

  getMovieSimilar(id: string) {
    this.moviesService.getMovieSimilar(id).subscribe((movieSimilarData) => {
      this.similarMovies = movieSimilarData.slice(0, 18);
    });
  }

  getMovieImages(id: string) {
    this.moviesService.getMovieImages(id).subscribe((movieImagesData) => {
      this.movieImages = movieImagesData;
    });
  }

  getMovieCredits(id: string) {
    this.moviesService.getMovieCredits(id).subscribe((movieCreditsData) => {
      this.movieCredits = movieCreditsData;
      this.directors = this.getDirectors(movieCreditsData.crew);
    });
  }

  getDirectors(crew: CrewMember[]): string[] {
    return crew.filter(member => member.job.toLowerCase() === 'director').map(director => director.name);
  }

  convertRuntime(runtime: number): string {
    const hours = Math.floor(runtime / 60);
    const minutes = runtime % 60;
    return `${hours}h ${minutes}m`;
  }

  getLanguageName(code: string): string {
    return this.languages[code.toLowerCase()] || code.toUpperCase();
  }


  getGenres(genres: { id: number, name: string }[]): string {
    return genres.map(genre => genre.name).join(', ');
  }
}