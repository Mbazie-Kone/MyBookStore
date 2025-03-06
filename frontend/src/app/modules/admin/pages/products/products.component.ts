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
    id: 0,
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: true,
    categoryName: '',
    imageUrls: [] as string[]
  };

  selectedFiles: File[] = [];
  previewImages: string[] = [];
  categories: any[] = []; // Array to store available categories

  successMessage: string = "";
  errorMessage: string = "";

  fileError: string = '';

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
  onFileSelected(event: Event) {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];

      if (!this.selectedFile.type.startsWith('image/')) {
        this.fileError = "Only image files are allowed.";
        this.selectedFile = null;
      }
      else {
        this.fileError = '';
      }
    }
    
  }

  // Method to upload the image and then add the product
  uploadImageAndSaveProduct(form: any) {
    if (!this.selectedFile) {
      this.errorMessage = "Please select an image.";

      return;
    }
    this.productService.uploadImage(this.selectedFile).subscribe({
      next: (imageUrl) => {
        this.product.imageUrl = imageUrl;

        this.addProduct(form);
        
      },
      error: (error) => {
        this.errorMessage = "Error uploading the image.";
        console.error(error);
      }
    });
  }

  // Method for adding the produc
  addProduct(form: any) {
    this.productService.addProduct(this.product).subscribe({
      next: (response) => {
        this.successMessage = "Product added successfully!";
        this.errorMessage = "";
        
        if (form) {
          form.resetForm();
        }

        this.selectedFile = null;

        console.log(response);

      },
      error: (error) => {
        this.successMessage = "";
        this.errorMessage = error.error;

        console.error(error);
      }
    });
  }
}
