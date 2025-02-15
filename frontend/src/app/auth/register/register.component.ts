import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';

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
    
  }

}
