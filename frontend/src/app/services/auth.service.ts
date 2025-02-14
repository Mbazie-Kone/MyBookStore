import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/auth/login'; // Gateway URL

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {

    return this.http.post<any>(this.apiUrl, { username, password });
  }
}
