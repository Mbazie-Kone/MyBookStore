import { Component } from '@angular/core';

@Component({
  selector: 'app-home',
  standalone: false,
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {
  categories = [
    {id:1, name: 'Frontend', description: 'Books' },
    {id:2, name: 'Backend', description: 'Books' },
    {id:3, name: 'Database', description: 'Books' }
  ];

  goToCategory(categoryId: number) {
    console.log('Navigate', categoryId);
  }
}
