import { Component, OnInit } from '@angular/core';
import { Product } from '../../../../models/product';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-product-details',
  standalone: false,
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent implements OnInit {
  product!: Product;
  errorMessage: string = '';
  selectedImage: string = '';

  constructor(private route: ActivatedRoute, private productService: ProductService) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.loadProductDetails(+productId);
    }
  }

  loadProductDetails(id: number) {
    this.productService.getProductById(id).subscribe({
      next: (data) => { this.product = data; },
      error: (error) => {
        this.errorMessage = "Error loading product details.";
        console.error(error);
      }
    });
  }
}
