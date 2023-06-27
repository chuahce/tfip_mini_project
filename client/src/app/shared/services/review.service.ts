import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  baseUrl: string = 'http://localhost:8080/api/review';

  constructor(private http: HttpClient) { }

  getReviews(movieId: string) {
    return this.http.get<any>(`${this.baseUrl}/movies/${movieId}`);
  }

  addReviews(feedback: any) {
    return this.http.post<any>(this.baseUrl, feedback);
  }
}
