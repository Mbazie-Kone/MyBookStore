import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth/login';

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    const body = { username, password };
    
    return this.http.post<any>(this.apiUrl, body, {
      headers: {'Content-Type': 'application/json'}
    });
  }
}
