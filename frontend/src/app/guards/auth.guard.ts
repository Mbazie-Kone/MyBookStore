import { inject } from '@angular/core';
import { CanActivateFn, Router, RouteReuseStrategy } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);

  const user = JSON.parse(localStorage.getItem('user') || '{}');

  if(user && user.role === 'ADMIN') {
    
    return true;
  }

  router.navigate(['/admin/login']);

  return false;
};
