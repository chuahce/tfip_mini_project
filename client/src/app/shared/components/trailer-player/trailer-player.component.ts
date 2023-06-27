import { Component, Input, OnChanges, OnInit } from '@angular/core';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-trailer-player',
  templateUrl: './trailer-player.component.html',
  styleUrls: ['./trailer-player.component.css']
})
export class TrailerPlayerComponent implements OnInit, OnChanges {
  @Input() site: string = 'YouTube';
  @Input() key: string | null = null;

  trailerUrl: SafeResourceUrl = '';

  constructor(private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.updateTrailerUrl();
  }

  ngOnChanges(): void {
    this.updateTrailerUrl();
  }

  getSafeUrl(url: string) {
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  private updateTrailerUrl(): void {
    if (!this.key) {
      return;
    }

    switch (this.site) {
      case 'YouTube':
        this.trailerUrl = this.getSafeUrl('https://www.youtube.com/embed/' + this.key);
        break;
      case 'Vimeo':
        this.trailerUrl = this.getSafeUrl('https://www.vimeo.com/embed/' + this.key);
        break;
      default:
        this.trailerUrl = this.getSafeUrl('');
        break;
    }
  }
}