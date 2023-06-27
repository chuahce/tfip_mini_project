import { Component, Input } from '@angular/core';
import { Item } from '../../models/item';

@Component({
  selector: 'app-items-banner',
  templateUrl: './items-banner.component.html',
  styleUrls: ['./items-banner.component.css']
})
export class ItemsBannerComponent {
  @Input() items: Item[] = [];
  @Input() title: string = '';
}
