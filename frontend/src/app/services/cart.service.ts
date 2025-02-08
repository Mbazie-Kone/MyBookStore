import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cartKey = 'shopping_cart';

  constructor() { }

  //Add product to cart (saved in localStorage)
  addToCart(product: any) {
    let cart = this.getCart();
    cart.push(product);
    localStorage.setItem(this.cartKey, JSON.stringify(cart));
  }

  //Retrieve the cart from localStorage
  getCart() {

    return JSON.parse(localStorage.getItem(this.cartKey) || '[]');
  }

  //Sync the cart after login
  syncCartWithBackend(userId: string, backendCart: any[]) {
    const localCart = this.getCart();
  
    //Merge products without duplicates
    const mergeCart = [...new Map([...localCart, ...backendCart].map(item => [item.id, item])).values()];

    //Save to the backend and localStorage
    localStorage.setItem(this.cartKey, JSON.stringify(mergeCart));
  }

  // Empty cart (after logout or completed order)
  clearCart() {
    localStorage.removeItem(this.cartKey);
  }
}
