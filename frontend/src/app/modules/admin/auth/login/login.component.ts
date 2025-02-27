import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserAdminService } from '../../../../services/user-admin.service';

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
        localStorage.setItem('token', response.token);
        this.router.navigate(['/admin/dashboard']);
      },
      error: (err) => {
        this.errorMessage = 'login failed. Invalid credentials!', err;
        console.log(err);
      }
    });
  }
}
