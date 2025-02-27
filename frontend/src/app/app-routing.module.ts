import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layout/layout.component';

const routes: Routes = [
  {
    path:'', component: LayoutComponent,
    children: [
      { path:'home', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule) },
      { path:'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule) },
      { path:'cart', loadChildren: () => import('./modules/cart/cart.module').then(m => m.CartModule) },
      { path:'account', loadChildren: () => import('./modules/account/account.module').then(m => m.AccountModule) }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
