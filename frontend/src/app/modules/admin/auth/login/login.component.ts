import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserAdminService } from '../../../../services/user-admin.service';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private userAdminService: UserAdminService, private router: Router) {}

  login() {
    this.userAdminService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log("Login ok", response.token);
        localStorage.setItem('token', response.token);

        const decodedToken: any = jwtDecode(response.token);
        console.log(decodedToken);

        this.router.navigate(['/admin/dashboard']).then(success => {
          console.log(success);
        }).catch(err => {
          console.error(err);
        });
      },
      error: (err) => {
        console.log(err);
        this.errorMessage = 'login failed. Invalid credentials!';   
      }
    });
  }
}
