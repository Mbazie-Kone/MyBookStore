import { Component } from '@angular/core';
import { Product } from '../../../../models/product';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-product-details',
  standalone: false,
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {
  product: Product | null = null;
  errorMessage: string = '';

  constructor(private route: ActivatedRoute, private productService: ProductService) {}
}
