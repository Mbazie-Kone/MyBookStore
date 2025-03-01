import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private addProductUrl = 'http://localhost:8080/api/catalog/insert/product'

  constructor() { }
}
