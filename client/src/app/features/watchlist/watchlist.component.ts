import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from 'src/app/shared/models/item';
import { WatchList } from 'src/app/shared/models/watchlist';
import { StorageService } from 'src/app/shared/services/storage.service';
import { WatchlistService } from 'src/app/shared/services/watchlist.service';


@Component({
  selector: 'app-watchlist',
  template: `
    <button pButton icon="pi pi-bookmark" [class]="watchlistButtonClass" (click)="toggleWatchlist()">
        <strong class="pl-2">{{buttonLabel}}</strong>
    </button>
  `,
  styles: []
})
export class WatchlistComponent implements OnInit {
  @Input() item: Item | null = null;
  @Input() isLoggedIn?: boolean;

  watchlistIcon = 'pi pi-bookmark';
  watchlistButtonClass = 'p-button-primary';
  buttonLabel = 'Add to Watchlist';

  isMovieInWatchlist = false;

  constructor(private watchlistService: WatchlistService, private storage: StorageService,
    private router: Router,
    private route: ActivatedRoute) {
    this.checkMovieInWatchlist();
  }
  ngOnInit(): void {
  }

  toggleWatchlist() {

    if (this.isLoggedIn !== true) {
      this.router.navigate(['../../auth/signin'], { relativeTo: this.route });
    }

    if (this.isMovieInWatchlist) {
      // Remove the movie from watchlist
      this.watchlistService.deleteWatchList(this.item?.id.toString()).subscribe(() => {
        this.watchlistButtonClass = 'p-button-primary';
        this.buttonLabel = 'Add to Watchlist';
        this.isMovieInWatchlist = false;
      });
    } else {
      // Add the movie to watchlist
      const watchList: WatchList = {
        movieId: this.item?.id.toString(),
        posterPath: this.item?.poster_path,
        title: this.item?.original_title,
        userId: this.storage.getUser().id
      };
      this.watchlistService.saveWatchList(watchList).subscribe(() => {
        this.watchlistButtonClass = 'p-button-warning';
        this.buttonLabel = 'Remove from Watchlist';
        this.isMovieInWatchlist = true;

      });
    }
  }

  checkMovieInWatchlist() {
    this.watchlistService.getWatchListsByUser(this.storage.getUser().id).subscribe((res) => {
      if (res && res.filter(m => m.movieId == this.item?.id).length > 0) {
        this.buttonLabel = 'Remove from Watchlist';
        this.isMovieInWatchlist = true;
        this.watchlistButtonClass = 'p-button-warning';
      } else {
        this.buttonLabel = 'Add to Watchlist';
        this.isMovieInWatchlist = false;
        this.watchlistButtonClass = 'p-button-primary';
      }
    });
  }
}
