import { Component, OnInit } from '@angular/core';
import { Product } from '../../../../models/product';
import { ProductService } from '../../../../services/product.service';

@Component({
  selector: 'app-view-products',
  standalone: false,
  templateUrl: './view-products.component.html',
  styleUrl: './view-products.component.css'
})
export class ViewProductsComponent implements OnInit {
  products: Product[] = [];
  // errorMessage: string = "";

  constructor(private productService: ProductService) {}
  
  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts() {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data.map(product => ({
          ...product,
          imageUrls: product.imageUrls || []
        }));
      },
      error: (error) => {
        // this.errorMessage = "Error loading products.";
        console.error(error);
      }
    });
  }

  deleteProduct(id: number) {
    if (confirm("Are you sure you want to delete this product?")) {
      this.productService.deleteProduct(id).subscribe({
        next: () => {
          this.products = this.products.filter(product => product.id !== id);
          console.log(`Product with ID ${id} deleted successfully`);
        },
        error: (error) => {
          console.error("Error deleting product:", error);
        }
      });
    }
  }

}
