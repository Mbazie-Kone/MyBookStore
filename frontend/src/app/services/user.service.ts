import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8081/api/auth/register';

  constructor(private http: HttpClient) { }

  registerUser(username: string, password:string, role: string): Observable<{ message: string; role: string}> {
    const body = {username, password, role};

    return this.http.post<{ message: string; role: string}>(this.apiUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }
}
