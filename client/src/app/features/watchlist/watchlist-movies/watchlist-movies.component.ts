import { Component, OnInit } from '@angular/core';
import { IMAGES_SIZES } from 'src/app/shared/constants/images-sizes';
import { WatchList } from 'src/app/shared/models/watchlist';
import { StorageService } from 'src/app/shared/services/storage.service';
import { WatchlistService } from 'src/app/shared/services/watchlist.service';

@Component({
  selector: 'app-watchlist-movies',
  templateUrl: './watchlist-movies.component.html',
  styleUrls: ['./watchlist-movies.component.css']
})
export class WatchListMoviesComponent implements OnInit {
  watchlist: WatchList[] = [];
  imagesSizes = IMAGES_SIZES;

  constructor(private watchListService: WatchlistService, private storage: StorageService) { }

  ngOnInit(): void {
    this.watchListService.getWatchListsByUser(this.storage.getUser().id).subscribe((res) => this.watchlist = res)
  }

  removeFromWatchlist(item: WatchList, i: number) {
    this.watchListService.deleteWatchList(item.movieId).subscribe(() => {
      console.log(this.watchlist.length);
      this.watchlist.splice(i, 1)
    });
    console.log(this.watchlist.length);

  }
}
