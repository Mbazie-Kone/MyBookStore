import { Component, OnInit } from '@angular/core';
import { Product } from '../../../../models/product';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-catalog',
  standalone: false,
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.css'
})
export class CatalogComponent implements OnInit {
  products: Product[] = [];
  errorMessage: string = '';

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data.map(product => ({
          ...product,
          mainImageUrl: product.imageUrls.length > 0 ? product.imageUrls[0].imageUrl : ''
        }));
      },
      error: (error) => {
        this.errorMessage = "Error loading products.";
        console.error(error);
      }
    });
  }
}
