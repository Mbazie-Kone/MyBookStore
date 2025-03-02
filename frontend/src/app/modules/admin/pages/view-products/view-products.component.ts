import { Component, OnInit } from '@angular/core';
import { Product } from '../../../../models/product';

@Component({
  selector: 'app-view-products',
  standalone: false,
  templateUrl: './view-products.component.html',
  styleUrl: './view-products.component.css'
})
export class ViewProductsComponent implements OnInit {
  products: Product[] = [];
  errormessage: string = "";
  
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

}
