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
  product: Product = {
    id: 0,
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: false,
    categoryName: '',
    imageUrls: []
  }
  errorMessage: string = '';
  selectedImage: string = '';
  loading: boolean = true;

  constructor(private route: ActivatedRoute, private productService: ProductService) {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.loadProductDetails(+productId);
    }
  }

  loadProductDetails(id: number) {
    this.loading = true; // Enable loading
    this.productService.getProductById(id).subscribe({
      next: (data) => { 
        this.product = data; 
        this.product.imageUrls = this.product.imageUrls || [];

        // Check if there are images and set the first one as selected
        if (this.product.imageUrls.length > 0) {
          this.selectedImage = this.product.imageUrls[0];
        } else {
          console.warn("No images found for this product.");
        }
        
        this.loading = false; // Disable loading when data is loaded
      },
      error: (error) => {
        this.errorMessage = "Error loading product details.";
        console.error(error);
        this.loading = false; // Disable loading even in case of an error
      }
    });
  }

  // Method to change the displayed image
  changeImage(imagUrl: string) {
    console.log("Selected Image URL:", imagUrl);
    this.selectedImage = imagUrl;
  }
}
