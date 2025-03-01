import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ProductService } from '../../../../services/product.service';

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

  selectedFile: File | null = null;

  constructor(private productService: ProductService) {}

  
}
