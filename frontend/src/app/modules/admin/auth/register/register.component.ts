import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      username: ['', [Validators.required, Validators.minLength(3)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      role: ['', Validators.required]
    });
  }

  onSubmit() {
    if(this.registerForm.valid) {
      this.userAdminService.register(this.registerForm.value).subscribe({
        next: (response) => {
          this.successMessage = response.message;
          this.errorMessage = '';
          this.registerForm.reset();
        },
        error: (err) => {
          this.successMessage = '';
          this.errorMessage = err.error?.Error || 'Registration failed';
        }
      });
    }
  }
}
