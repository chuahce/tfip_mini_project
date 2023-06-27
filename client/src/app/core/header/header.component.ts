import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faBookmark, faPlus } from '@fortawesome/free-solid-svg-icons';
import { faGithub } from '@fortawesome/free-brands-svg-icons';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/shared/services/storage.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  searchValue: string | null = null;

  faBookmark = faBookmark;
  faPlus = faPlus;
  faGithub = faGithub;
  isLoggedIn = new Observable();

  constructor(private router: Router, storageService: StorageService) {
    this.isLoggedIn = storageService.isLoggedIn.asObservable()
  }

  ngOnInit(): void { }

  searchChanged() {
    if (this.searchValue?.trim() !== '') {
      this.router.navigate(['/movies'], { queryParams: { q: this.searchValue, page: 1 } });
    }
  }
}
