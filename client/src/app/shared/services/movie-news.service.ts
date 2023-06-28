import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MovieNewsService {
  private baseUrl = `${environment.apiUrl}/news`;

  private categories: { [key: string]: string } = {
    boxOffice: "movies AND (worldwide box office OR Hollywood box office OR blockbuster)",
    hollywoodHighlights: "movies AND (Hollywood OR celebrity news OR castings OR director news)",
    asianSpotlight: "movies AND (Asian OR Chinese OR Korean OR Japanese OR Bollywood or Hong Kong OR HongKong OR China)",
    indieCorner: "movies AND (indie OR independent OR film festival OR Sundance OR Cannes)",
    imdb: "movies AND IMDb",
    rottenTomatoes: "movies AND Rotten Tomatoes",
    metacritic: "movies AND (Metacritic score OR metascore )",
    netflix: "movies AND Netflix",
    disneyPlus: "movies AND Disney+",
    appleTVPlus: "movies AND AppleTV+",
    starWars: "movies AND (Star Wars OR Lucasfilm)",
    mcu: "movies AND (MCU OR Marvel Cinematic Universe OR Marvel Universe OR Avengers)",
    dceu: "movies AND (DCEU OR DC Extended Universe OR Justice League OR DC Comics)",
    pixar: "movies AND (Pixar)",
    classicCinema: "movies AND (classic OR golden age OR retro OR nostalgia)",
    tvTalk: "movies AND (TV series OR season premiere OR season finale)",
    directorsCut: "movies AND (director news OR filmmaker OR behind the scenes)",
    sciFiAndFantasy: "movies AND (sci-fi OR fantasy)",
    documentaryDigest: "movies AND (documentaries OR true story OR non-fiction)",
    horrorHub: "movies AND (horror OR scary)",
    animationStation: "movies AND (animated OR animation OR CGI OR anime OR 3D animation)"
  };

  constructor(private http: HttpClient) { }

  private getNews(q: string, pageSize: number, page: number): Observable<any> {
    const date = new Date();
    date.setDate(date.getDate() - 7);
    const fromDate = date.toISOString().split('T')[0];

    const url = `${this.baseUrl}?q=${encodeURIComponent(q)}&from=${fromDate}&language=en&sortBy=relevancy&pageSize=${pageSize}&page=${page}`;
    return this.http.get(url).pipe(
      map((response: any) => {
        // Filter out articles without an image URL
        response.articles = response && response.articles && response.articles.filter((article: any) => article.urlToImage);
        return response;
      })
    );
  }

  getNewsByCategory(category: string, pageSize: number = 1, page: number = 1): Observable<any> {
    return this.getNews(category, pageSize, page);
  }
}

