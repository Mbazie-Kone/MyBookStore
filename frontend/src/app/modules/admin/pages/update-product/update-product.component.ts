import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../../../services/product.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-update-product',
  standalone: false,
  templateUrl: './update-product.component.html',
  styleUrl: './update-product.component.css'
})
export class UpdateProductComponent implements OnInit {

  product = {
    id: 0,
    name: '',
    description: '',
    price: 0,
    stock: 0,
    isAvailable: true,
    categoryName: '',
    imageUrl: ''
  };
  categories: any[] = [];
  selectedFile: File | null = null;
  successMessage: string = "";
  errorMessage: string = "";

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
    this.selectedFile = event.target.files[0];
  }

  updateProduct() {
    if (this.selectedFile) {
      this.productService.uploadImage(this.selectedFile).subscribe({
        next: (imageUrl) => {
          this.product.imageUrl = imageUrl;
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
      next: () => {
        this.successMessage = "Product updated successfully!";
        this.router.navigate(['/admin/view-products']);
      },
      error: () => {
        this.errorMessage = "Error updating product.";
      }
    });
  }
}
