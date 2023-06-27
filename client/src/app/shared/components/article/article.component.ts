import { Component, Input, OnInit } from '@angular/core';
import { Article } from '../../models/movie-news';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  @Input() article!: Article;

  constructor() { }

  ngOnInit(): void {
  }

  navigateToArticle(url: string): void {
    window.location.href = url;
  }
}
