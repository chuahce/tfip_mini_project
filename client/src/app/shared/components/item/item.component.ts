import { Component, Input, OnInit } from '@angular/core';
import { IMAGES_SIZES } from '../../constants/images-sizes';
import { Item } from '../../models/item';
import { faStar } from '@fortawesome/free-solid-svg-icons';
// import { faBookmark } from '@fortawesome/free-regular-svg-icons';
// import { faPlus } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent implements OnInit {
  @Input() itemData: Item | null = null;

  imagesSizes = IMAGES_SIZES;

  faStar = faStar;
  // faBookmark = faBookmark;
  // faPlus = faPlus;

  constructor() { }

  ngOnInit(): void { }
}
