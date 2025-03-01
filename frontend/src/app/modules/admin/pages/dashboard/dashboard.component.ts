import { Component, OnInit } from '@angular/core';
import { UserAdminService } from '../../../../services/user-admin.service';

@Component({
  selector: 'app-dashboard',
  standalone: false,
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  isAdmin: boolean = false;

  constructor(private userAdminService: UserAdminService) {}

  ngOnInit(): void {
    //this.isAdmin = this.userAdminService.hasRole('ADMIN');
  }

}
