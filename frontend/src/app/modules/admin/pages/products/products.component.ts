import { Component } from '@angular/core';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent {
  product = {
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: true,
    categoryNames: ['Java', 'C#'],
    imageUrl: ''
  };

  selectedFile: File | null = null;

  constructor(private productService: ProductService) {}

  // Method for selecting the image
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  // Method to upload the image and then add the product
  uploadImageAndSaveProduct() {
    if (!this.selectedFile) {
      return;
    }
    this.productService.uploadImage(this.selectedFile).subscribe((imageUrl) => {
      this.product.imageUrl = imageUrl;
      this.addProduct();
    }, (error) => {
      console.error('Error loading image\'image:', error);
    });
  }

  // Method for adding the produc
  addProduct() {
    this.productService.addProduct(this.product).subscribe(response => {
      console.log('Product added:', response);
    }, error => {
      console.error('Error', error);
    });
  }
}
