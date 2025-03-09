import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Product } from '../models/product';
import { Observable } from 'rxjs';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private ProductUrl = 'http://localhost:8080/api/catalog';

  constructor(private http: HttpClient) { }

  // Method to get all categories
  getCategories(): Observable<Category[]> {

    return this.http.get<Category[]>(`${this.ProductUrl}/view/categories`);
  }

  // Method for uploading the image
  uploadImage(file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.ProductUrl}/upload/image`, formData, { responseType: 'text'});
  }

  // Method for adding a product
  addProduct(product: Product): Observable<Product> {

    return this.http.post<Product>(`${this.ProductUrl}/insert/product`, product);
  }

  // View all products
  getProducts(): Observable<Product[]> {

    return this.http.get<Product[]>(`${this.ProductUrl}/view/products`).pipe(
      map((products: Product[]) => products.map((product: Product) => ({
        ...product,
        imageUrls: product.imageUrls ?? []
      })))
    );
  }

  // Get product by id
  getProductById(id: number): Observable<Product> {

    return this.http.get<Product>(`${this.ProductUrl}/product/${id}`);
  }

  // Update
  updateProduct(id: number, product: Product): Observable<any> {

    return this.http.put(`${this.ProductUrl}/update/product/${id}`, product);
  }

  // Delete
  deleteProduct(id: number): Observable<void> {

    return this.http.delete<void>(`${this.ProductUrl}/delete/product/${id}`);
  }

  // Delete Image
  deleteImage(imageUrl: string): Observable<any> {
    
    return this.http.delete(`${this.ProductUrl}/delete/image?imageUrl=${encodeURIComponent(imageUrl)}`);
  }
}
