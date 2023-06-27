import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WatchList } from '../models/watchlist';


@Injectable({
  providedIn: 'root'
})
export class WatchlistService {
  baseUrl: string = 'http://localhost:8080/api/watchlist';

  constructor(private http: HttpClient) { }

  saveWatchList(watchList: WatchList): Observable<WatchList> {
    return this.http.post<WatchList>(`${this.baseUrl}/`, watchList);
  }

  getWatchListsByUser(userId: number): Observable<WatchList[]> {
    return this.http.get<WatchList[]>(`${this.baseUrl}/user/${userId}`);
  }

  deleteWatchList(id: any): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
