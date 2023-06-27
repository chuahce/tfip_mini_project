import { Component, Input } from '@angular/core';
import { MovieScores, MovieSearchResult, Rating } from '../../models/movie-scores';
import { faClapperboard } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-movie-scores-card',
  templateUrl: './movie-scores-card.component.html',
  styleUrls: ['./movie-scores-card.component.css']
})
export class MovieScoresCardComponent {
  @Input() movie!: MovieScores;

  faClapperboard = faClapperboard;

  constructor() { }

  ngOnInit(): void {
  }

  getRating(source: string): Rating | undefined {
    return this.movie.Ratings.find(rating => rating.Source === source);
  }

  transformRuntime(runtime: string): string {
    let runtimeInMinutes = parseInt(runtime.replace(' min', ''), 10);
    let hours = Math.floor(runtimeInMinutes / 60);
    let minutes = runtimeInMinutes % 60;
    return `${hours}h ${minutes}m`;
  }
}
