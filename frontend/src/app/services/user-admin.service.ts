import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAdminService {

  private apiLoginUrl = 'http://localhost:8080/api/auth/login';

  private apiRegisterUrl = 'http://localhost:8080/api/auth/register';

  constructor(private http: HttpClient) { }
}
