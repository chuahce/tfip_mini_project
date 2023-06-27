import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { IMAGES_SIZES } from '../../constants/images-sizes';
import { Item } from '../../models/item';
import { faStar } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-slider',
  templateUrl: './slider.component.html',
  styleUrls: ['./slider.component.css'],
})
export class SliderComponent implements OnInit, OnDestroy {
  @Input() items: Item[] = [];
  @Input() isBanner: boolean = false;

  faStar = faStar;

  currentSlideIndex: number = 0;
  autoSlideInterval?: number;

  readonly imagesSizes = IMAGES_SIZES;

  ngOnInit(): void {
    if (!this.isBanner) {
      this.autoSlideInterval = window.setInterval(() => this.nextSlide(), 5000);
    }
  }

  ngOnDestroy(): void {
    if (this.autoSlideInterval !== undefined) {
      window.clearInterval(this.autoSlideInterval);
    }
  }

  nextSlide() {
    this.currentSlideIndex = (this.currentSlideIndex + 1) % this.items.length;
  }

  prevSlide() {
    this.currentSlideIndex = (this.currentSlideIndex - 1 + this.items.length) % this.items.length;
  }

  goToSlide(index: number) {
    this.currentSlideIndex = index;
  }
}
