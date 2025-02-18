import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = '/api/auth';

  constructor(private http: HttpClient) { }

  /*login(username: string, password: string): Observable<any> {
    const body = { username, password };
    
    return this.http.post<any>(this.apiUrl, body, {
      headers: {'Content-Type': 'application/json'}
    });
  }*/

  login(username: string, password: string): Observable<any> {

    return this.http.post<any>(`${this.apiUrl}/login`, { username, password}, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }

  register(username: string, password: string, role: string): Observable<any> {

    return this.http.post<any>(`${this.apiUrl}/register`, { username, password, role }, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
}
