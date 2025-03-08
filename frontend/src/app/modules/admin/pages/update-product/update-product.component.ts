import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/product.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../../../models/product';
import { Category } from '../../../../models/category';

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
  categories: Category[] = [];
  successMessage: string = "";
  errorMessage: string = "";
  maxImages: number = 10;
  selectedImage: string = '';

  constructor(private productService: ProductService, private route: ActivatedRoute) {}
  
  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    if (productId) {
      this.loadProductDetails(+productId);
    }
    this.loadCategories();
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

  loadCategories() {
    this.productService.getCategories().subscribe({
      next: (data: Category[]) => {
       this.categories = data;
      },
      error: (error) => {
        console.error("Error loading categories:", error);
      }
    });
  }

  // Select the image to upload
  onFileSelected(event: any) {
    const file = event.target.files[0];
    if (file) {
      this.uploadImage(file);
    }
  }

  // Uploading a new image
  uploadImage(file : File) {
    if (this.product.imageUrls.length >= this.maxImages) {
        alert("You can't upload more than 10 images.");
        
        return;
    }
    
    if (this.product.imageUrls.length >= this.maxImages) {
      this.errorMessage = "Maximum number of images reached.";

      return;
    }

    this.productService.uploadImage(file).subscribe({
      next: (imageUrl) => {
        this.product.imageUrls.push(imageUrl);
      },
      error: (error) => {
        console.error("Error uploading image:", error);
      }
    });
  }

  // Delete an image
  deleteImage(imageUrl: string) {
    if (confirm("Are you sure you want to delete this image?")) {
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
   
  }

  // Update the product
  updateProduct() {
    this.productService.updateProduct(this.product.id, this.product).subscribe({
      next: () => {
        this.successMessage = "Product updated successfully!";
      },
      error: (error) => {
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
