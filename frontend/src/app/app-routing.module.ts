import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layout/layout.component';
import { authGuard } from './guards/auth.guard';

const routes: Routes = [
  {
    path:'',
    component: LayoutComponent,
    children: [
      { path:'', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },
      { path:'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule) },
      { path:'cart', loadChildren: () => import('./modules/cart/cart.module').then(m => m.CartModule) },
      { path:'account', loadChildren: () => import('./modules/account/account.module').then(m => m.AccountModule) },
      { path: 'admin', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule), canActivate: [authGuard] }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
