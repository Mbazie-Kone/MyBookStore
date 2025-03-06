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

  // Multiple image selection
  onFileSelected(event: any) {
    if (event.target.files,length > 10) {
      alert('You can upload a maximum of 10 images.');

      return;
    }

    this.selectedFiles = Array.from(event.target.files);
    this.previewImages = [];

    for (let file of this.selectedFiles) {
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.previewImages.push(e.target.result);
      };
      reader.readAsDataURL(file);
    }
  }

  // Upload multiple images and save the product
  uploadImageAndSaveProduct(form: any) {
    if (this.selectedFiles.length > 0) {
      let uploadedUrls: string[] = [];

      this.selectedFiles.forEach((file, index) => {
        this.productService.uploadImage(file).subscribe({
          next: (imageUrl) => {
            uploadedUrls.push(imageUrl);

            // When all images are uploaded, save the product
            if(uploadedUrls.length === this.selectedFiles.length) {
              this.product.imageUrls = uploadedUrls;
              this.addProduct(form);
            }
          },
          error: (error) => { console.error('Error uploading image:', error); }
        });
      });
    } else {
      this.addProduct(form); // If no image is selected, save only the product.
    }
  }

  // Method for adding the produc
  addProduct(form: any) {
    this.productService.addProduct(this.product).subscribe({
      next: (response) => {
        this.successMessage = "Product added successfully!";
        this.errorMessage = "";
        this.selectedFiles = [];
        this.previewImages = [];

        if (form) {
          form.resetForm();
        } 
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
