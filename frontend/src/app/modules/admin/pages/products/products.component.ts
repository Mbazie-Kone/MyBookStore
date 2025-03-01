import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  product = {
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: true,
    categoryName: ['Java', 'C#'],
    categoryDescription: '',
    imageUrl: ''
  };

  constructor(private http: HttpClient) {
    
  }
}
