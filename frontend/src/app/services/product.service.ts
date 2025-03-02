import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private addProductUrl = 'http://localhost:8080/api/catalog';

  constructor(private http: HttpClient) { }

  // Method to get all categories
  getCategories(): Observable<any[]> {

    return this.http.get<any[]>(`${this.addProductUrl}/categories`);
  }

  // Method for uploading the image
  uploadImage(file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.addProductUrl}/upload-image`, formData, { responseType: 'text'});
  }

  // Method for adding a product
  addProduct(product: Product): Observable<Product> {

    return this.http.post<Product>(`${this.addProductUrl}/products`, product);
  }
}
