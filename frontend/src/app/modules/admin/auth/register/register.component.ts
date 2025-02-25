import { Component } from '@angular/core';
import { UserAdminService } from '../../services/user-admin.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  /*username: string = '';
  password: string = '';
  role: string = 'USER';
  message: string = '';

  availableRoles: string[] = ['USER', 'ADMIN'];

  constructor(private userAdminService: UserAdminService) {}

  register() {
    if (!this.username || !this.password || !this.role) {
      this.message = "Insert username, password and select a role!";

      return;
    }

    this.userAdminService.register(this.username, this.password, this.role).subscribe({
      next: (response) => {
        console.log("✅ Registration successfully:", response);
        this.message = response.message;
      },
      error: (err) => {
        console.error("❌ Erro!", err);
        this.message = err.erro?.error || "❌ registration failed!";
      }
    });
  }*/

}
