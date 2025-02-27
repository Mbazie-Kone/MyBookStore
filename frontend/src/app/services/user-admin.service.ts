import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserAdminService {

  private apiLoginUrl = 'http://localhost:8080/api/admin/login';

  //private apiRegisterUrl = 'http://localhost:8080/api/admin/register';

  constructor(private http: HttpClient) { }

  login(username: string, password: string): Observable<any> {
    const body = { username, password };
    console.log(body);
      
    return this.http.post<any>(this.apiLoginUrl, body, {
      headers: {'Content-Type': 'application/json'}
    });
  }

  /*register(username: string, password:string, role: string): Observable<{ message: string; role: string}> {
    const body = {username, password, role};
  
    return this.http.post<{ message: string; role: string}>(this.apiRegisterUrl, body, {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    });
  }*/
}
