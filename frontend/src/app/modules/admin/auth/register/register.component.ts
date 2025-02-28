import { Component } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { UserAdminService } from '../../../../services/user-admin.service';

@Component({
  selector: 'app-register',
  standalone: false,
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup;
  roles = ['USER', 'ADMIN'];

  successMessage: string = '';
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private userAdminService: UserAdminService) {
    this.registerForm = this.fb.group({

    })
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
