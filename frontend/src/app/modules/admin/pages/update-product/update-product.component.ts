import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../../../../models/product';

@Component({
  selector: 'app-update-product',
  standalone: false,
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.css'
})
export class UpdateProductComponent implements OnInit {
  product: Product = {
    id: 0,
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: false,
    categoryName: '',
    imageUrls: []
  };
  categories: any[] = [];
  selectedFiles: [] = [];
  previewImages: string[] = [];
  successMessage: string = "";
  errorMessage: string = "";
  fileError: string ="";

  constructor(private productService: ProductService, private route: ActivatedRoute, private router: Router) {}
  
  ngOnInit(): void {
    const productId = Number(this.route.snapshot.paramMap.get('id'));
    if (productId) {
      this.productService.getProductById(productId).subscribe({
        next: (data) => { this.product = data; },
        error: (error) => { console.error('Error fetching product:', error); }
      });
      this.loadCategories();
    }
  }

  loadCategories() {
    this.productService.getCategories().subscribe({
      next: (data) => { this.categories = data; },
      error: (error) => { console.error('Error fetching categories:', error); }
    });
  }

  onFileSelected(event: any) {
    this.selectedFiles = event.target.files[0];
    if (this.selectedFiles && !this.selectedFiles.type.startsWith('image/')) {
      this.fileError = "Invalid file type. Please selcet an image file.";
      this,this.selectedFiles = null;
    } else {
      this.fileError = "";
    }
  }

  updateProduct() {
    if (this.selectedFiles) {
      this.productService.uploadImage(this.selectedFiles).subscribe({
        next: (imageUrl) => {
          this.product.imageUrls = imageUrl;
          this.sendUpdateRequest();
        },
        error: () => {
          this.errorMessage = "Error uploading image.";
        }
      });
    } else {
      this.sendUpdateRequest();
    }
  }

  sendUpdateRequest() {
    this.productService.updateProduct(this.product.id!, this.product).subscribe({
      next: (response: any) => {
        if (response && response.message) {
          this.successMessage = response.message
        } else {
          this.successMessage = "Product update successfully!";
        }
        this.errorMessage = "";

        this.router.navigate(['/admin/view-products']);
      },
      error: (error) => {
        if (error.error && error.error.error) {
          this.errorMessage = error.error.error;
        } else {
          this.errorMessage = "Error updating product.";
        } 
      }
    });
  }
}
