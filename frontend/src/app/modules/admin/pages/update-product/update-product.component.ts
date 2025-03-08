import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/product.service';
import { ActivatedRoute } from '@angular/router';
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
  selectedFile: File | null = null;
  successMessage: string = "";
  errorMessage: string = "";
  maxImages: number = 10;
  selectedImage: string = '';
  fileError: string ="";

  constructor(private productService: ProductService, private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.loadProductDetails(+productId);
    }
  }

  loadProductDetails(id: number) {
    this.productService.getProductById(id).subscribe({
      next: (data) => { 
        this.product = data; 
        this.selectedImage = this.product.imageUrls.length > 0 ? this.product.imageUrls[0] : '';
      },
      error: (error) => { 
        this.errorMessage = "Error loading product details.";
        console.error(error); 
      }
    });
  }

  // Select the image to upload
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // Uploading a new image
  uploadImage() {
    if (!this.selectedFile) {
        this.errorMessage = "Please select an image.";
        
        return;
    }
    
    if (this.product.imageUrls.length >= this.maxImages) {
      this.errorMessage = "Maximum number of images reached.";

      return;
    }

    this.productService.uploadImage(this.selectedFile).subscribe({
      next: (imageUrl) => {
        this.product.imageUrls.push(imageUrl);
        this.selectedFile = null;
        this.errorMessage = "";
      },
      error: (error) => {
        this.errorMessage = "Error uploading the image.";
        console.error(error);
      }
    });
  }

  // Delete an image
  deleteImage(imageUrl: string) {
    this.productService.deleteImage(imageUrl).subscribe({
      next: () => {
        this.product.imageUrls = this.product.imageUrls.filter(img => img !== imageUrl);

        // If the main image has been deleted, select the first available one
        if (this.selectedImage === imageUrl) {
          this.selectedImage = this.product.imageUrls.length > 0 ? this.product.imageUrls[0] : '';
        }
      },
      error: (error) => {
        console.error("Error deleting image:", error);
      }
    });
  }

  // Update the product
  updateProduct() {
    this.productService.updateProduct(this.product.id, this.product).subscribe({
      next: () => {
        this.successMessage = "Product updated successfully!";
        this.errorMessage = "";
      },
      error: (error) => {
        this.successMessage = "";
        this.errorMessage = "Error updating product.";
        console.error(error);
      }
    });
  }

  // Select a main image
  selectMainImage(imageUrl: string) {
    this.selectedImage = imageUrl;
  }
}
