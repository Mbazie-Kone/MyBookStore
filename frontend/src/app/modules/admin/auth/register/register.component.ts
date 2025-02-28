import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup | undefined;
  roles = ['USER', 'ADMIN'];

  successMessage: string = '';
  errorMessage: string = '';

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
  }

}
