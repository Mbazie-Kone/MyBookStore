import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAdminService {

  private apiLoginUrl = 'http://localhost:8080/api/admin/login';

  private apiRegisterUrl = 'http://localhost:8080/api/admin/register';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    const body = { username, password };
      
    return this.http.post<any>(this.apiLoginUrl, body, {
      headers: {'Content-Type': 'application/json'}
    }).pipe(
      tap(response =>{
        localStorage.setItem('token', response.token);

        this.http.get<any>('http://localhost:8080/api/admin/me', {
          headers: new HttpHeaders({
            'Authorization': `Bearer ${response.token}`
          })
        }).subscribe(user =>{
          localStorage.setItem('user', JSON.stringify(user));
        });
      })
    );
  }

  register(userData: any): Observable<any> {
  
    return this.http.post<any>(this.apiRegisterUrl, userData);
  }

  /*getUserRoles(): string[] {
    const user = JSON.parse(localStorage.getItem('user') || '{}');

    return user.roles || [];
  }

  hasRole(role: string): boolean {

    return this.getUserRoles().includes(role);
  }

  isAuthenticated(): boolean {

    return !!localStorage.getItem('user');
  }*/
}
