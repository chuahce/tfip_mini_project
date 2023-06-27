import { Component, ViewEncapsulation, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Observable, map, of } from 'rxjs';
import { Article, NewsResponse } from 'src/app/shared/models/movie-news';
import { MovieNewsService } from 'src/app/shared/services/movie-news.service';


@Component({
  selector: 'app-movie-news',
  templateUrl: './movie-news.component.html',
  styleUrls: ['./movie-news.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class MovieNewsComponent implements OnInit {
  categories: MenuItem[] = []; // initialized as empty array
  selectedCategory: string = ''; // initialized as empty string
  articles: Article[] = [];
  visibleSidebar: boolean = false;

  // Pagination properties
  totalArticles = 0;
  currentPage = 1;

  constructor(private newsService: MovieNewsService) { }

  ngOnInit(): void {
    this.categories = [
      { label: 'Box Office', command: () => { this.selectCategory('boxOffice') } },
      { label: 'Hollywood Highlights', command: () => { this.selectCategory('hollywoodHighlights') } },
      { label: 'Asian Spotlight', command: () => { this.selectCategory('asianSpotlight') } },
      { label: 'Indie Corner', command: () => { this.selectCategory('indieCorner') } },
      { label: 'IMDb', command: () => { this.selectCategory('imdb') } },
      { label: 'Rotten Tomatoes', command: () => { this.selectCategory('rottenTomatoes') } },
      { label: 'Metacritic', command: () => { this.selectCategory('metacritic') } },
      { label: 'Netflix', command: () => { this.selectCategory('netflix') } },
      { label: 'Disney Plus', command: () => { this.selectCategory('disneyPlus') } },
      { label: 'Apple TV Plus', command: () => { this.selectCategory('appleTVPlus') } },
      { label: 'Star Wars', command: () => { this.selectCategory('starWars') } },
      { label: 'Marvel Cinematic Universe', command: () => { this.selectCategory('mcu') } },
      { label: 'DC Extended Universe', command: () => { this.selectCategory('dceu') } },
      { label: 'Pixar Animation Studios', command: () => { this.selectCategory('pixar') } },
      { label: 'Classic Cinema', command: () => { this.selectCategory('classicCinema') } },
      { label: 'TV Talk', command: () => { this.selectCategory('tvTalk') } },
      { label: 'Directors Cut', command: () => { this.selectCategory('directorsCut') } },
      { label: 'Sci-Fi and Fantasy', command: () => { this.selectCategory('sciFiAndFantasy') } },
      { label: 'Documentary Digest', command: () => { this.selectCategory('documentaryDigest') } },
      { label: 'Horror Hub', command: () => { this.selectCategory('horrorHub') } },
      { label: 'Animation Station', command: () => { this.selectCategory('animationStation') } },
    ];

    let initialCategory = this.categories.find(category => category.label === 'Netflix');

    if (initialCategory && initialCategory.label) {
      this.selectCategory(initialCategory.label.toLowerCase().replace(' ', ''));
    } else {
      console.error('Initial category not found in the categories array');
    }
  }

  loadArticles(category: string, page: number): void {
    this.newsService.getNewsByCategory(category, 2, page).subscribe({
      next: (newsResponse: NewsResponse) => {
        this.articles = newsResponse.articles;
        this.totalArticles = newsResponse.totalResults;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  selectCategory(category: string): void {
    this.selectedCategory = category;
    this.loadArticles(this.selectedCategory, this.currentPage);
  }

  paginate(event: { page: number, first: number, rows: number, pageCount: number }): void {
    this.currentPage = event.page + 1; // event.page is zero-based
    this.loadArticles(this.selectedCategory, this.currentPage);
  }
}
