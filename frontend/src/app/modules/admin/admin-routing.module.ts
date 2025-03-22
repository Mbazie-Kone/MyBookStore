import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { authGuard } from '../../guards/auth.guard';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProductsComponent } from './pages/products/products.component';
import { UpdateProductComponent } from './pages/update-product/update-product.component';
import { ViewProductsComponent } from './pages/view-products/view-products.component';

const routes: Routes = [
  { path: 'admin', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'admin/view-products', component: ViewProductsComponent },
  { path: 'admin/products', component: ProductsComponent },
  { path: 'admin/update-product/:id', component: UpdateProductComponent }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
