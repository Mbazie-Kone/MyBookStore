import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit {
  product = {
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: true,
    categoryName: '',
    imageUrl: ''
  };

  selectedFile: File | null = null;
  categories: any[] = []; // Array to store available categories

  successMessage: string = "";
  errorMessage: string = "";

  constructor(private productService: ProductService) { }

  ngOnInit() {
    this.loadCategories();
  }

  loadCategories() {
    this.productService.getCategories().subscribe({
      next: (data) => { this.categories = data; },
      error: (error) => { console.error('Error:', error); },
      complete: () => { console.log('Completed'); }
    });
  }

  // Method for selecting the image
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // Method to upload the image and then add the product
  uploadImageAndSaveProduct() {
    if (!this.selectedFile) {
      this.errorMessage = "Please select an image.";

      return;
    }
    this.productService.uploadImage(this.selectedFile).subscribe({
      next: (imageUrl) => {
        this.product.imageUrl = imageUrl;

        this.addProduct();
        
      },
      error: (error) => {
        this.errorMessage = "Error uploading the image.";
        console.error(error);
      }
    });
  }

  // Method for adding the produc
  addProduct() {
    this.productService.addProduct(this.product).subscribe({
      next: (response) => {
        console.log('Product added:', response)

        this.product = {
          name: '',
          description: '',
          price: 0,
          stock: 0,
          isAvailable: true,
          categoryName: '',
          imageUrl: ''
        };

        this.selectedFile = null;

      },
      error: (error) => {
        console.error('Error', error);

        this.product = {
          name: '',
          description: '',
          price: 0,
          stock: 0,
          isAvailable: true,
          categoryName: '',
          imageUrl: ''
        };

        this.selectedFile = null;

      }
    });
  }
}
