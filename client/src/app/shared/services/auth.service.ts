import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserInfoResponse } from '../models/user-info-response';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  baseUrl: string = 'http://localhost:8080/api/auth';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<UserInfoResponse> {
    const loginRequest = {
      username,
      password
    };

    return this.http.post<any>(`${this.baseUrl}/signin`, loginRequest);
  }

  signUp(username: string, password: string): Observable<any> {
    const signUpRequest = {
      username: username,
      password: password,
      email: username
    };
    return this.http.post<any>(`${this.baseUrl}/signup`, signUpRequest);
  }

  signOut(): Observable<any> {

    return this.http.get<any>(`${this.baseUrl}/signout`);
  }
}
