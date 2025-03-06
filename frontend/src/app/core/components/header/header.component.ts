import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: false,
  templateUrl: './header.component.html',
  styleUrl: './header.component.css',
  animations: [
    trigger('cartBadge', [
      state('normal', style({ transform: 'scale(1)'})),
      state('added', style({ transform: 'scale(1.2)'})),
      transition('normal <=> added', [
        animate('200ms ease-in-out')
      ])
    ])
  ]
})
export class HeaderComponent {
  isAuthenticated: boolean = false;
  cartItemCount: number = 0;
  badgeState: string = 'normal';

  logout() {
    console.log('logout');
    this.isAuthenticated = false;
  }

  animateBadge() {
    this.badgeState = 'added';
    setTimeout(() => {
      this.badgeState = 'normal';
    }, 200);
  }
}
