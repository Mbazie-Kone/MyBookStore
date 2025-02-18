import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  password: string = '';
  role: string = 'USER';
  message: string = '';

  availableRoles: string[] = ['USER', 'ADMIN'];

  constructor(private userService: UserService) {}

  registerUser() {
    if (!this.username || !this.password || !this.role) {
      this.message = "Insert username, password and select a role!";

      return;
    }

    this.userService.registerUser(this.username, this.password, this.role).subscribe({
      next: (response) => {
        console.log("✅ Registration successfully:", response);
        this.message = response.message;
      },
      error: (err) => {
        console.error("❌ Erro!", err);
        this.message = err.erro?.error || "❌ registration failed!";
      }
    });
  }

}
