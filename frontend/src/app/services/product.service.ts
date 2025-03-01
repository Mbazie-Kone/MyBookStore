import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private addProductUrl = 'http://localhost:8080/api/catalog/insert/product';

  constructor(private http: HttpClient) { }

  //Image
  uploadImage(file: File): Observable<string> {
    const formData = new FormData();
    formData.append('file', file);

    return this.http.post(`${this.addProductUrl}/upload-image`, formData, { responseType: 'text'});
  }

  //Product
  addProduct(product: any): Observable<any> {

    return this.http.post(`${this.addProductUrl}/products`, product);
  }
}
