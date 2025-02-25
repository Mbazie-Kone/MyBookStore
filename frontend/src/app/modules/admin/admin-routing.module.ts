import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { authGuard } from '../../guards/auth.guard';
import { ProductsComponent } from './pages/products/products.component';

const routes: Routes = [
  {
    path: 'admin',
    component: DashboardComponent,
    canActivate: [authGuard],
    children: [
      { path: 'products', component: ProductsComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
