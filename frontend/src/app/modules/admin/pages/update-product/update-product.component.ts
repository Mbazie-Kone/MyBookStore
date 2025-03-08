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
  selectedFile: File | null = null;
  successMessage: string = "";
  errorMessage: string = "";
  maxImgaes: number = 10;
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
    
    if (this.product.imageUrls.length >= this.maxImgaes) {
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
      }
    })
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
