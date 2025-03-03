import { Component } from '@angular/core';
import { Product } from '../../../../models/product';

@Component({
  selector: 'app-catalog',
  standalone: false,
  templateUrl: './catalog.component.html',
  styleUrl: './catalog.component.css'
})
export class CatalogComponent {
  products: Product[] = [];
}
