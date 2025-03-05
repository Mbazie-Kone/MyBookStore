import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './core/components/layout/layout.component';
import { ErrorComponent } from './errors/error/error.component';
import { LoginComponent } from './modules/admin/auth/login/login.component';
import { RegisterComponent } from './modules/admin/auth/register/register.component';
import { DashboardComponent } from './modules/admin/pages/dashboard/dashboard.component';
import { authGuard } from './guards/auth.guard';
import { ProductsComponent } from './modules/admin/pages/products/products.component';
import { ViewProductsComponent } from './modules/admin/pages/view-products/view-products.component';
import { UpdateProductComponent } from './modules/admin/pages/update-product/update-product.component';

const routes: Routes = [
  { path: 'admin/login', component: LoginComponent },
  { path: 'admin/register', component: RegisterComponent },
  { path: 'admin/dashboard', component: DashboardComponent, canActivate: [authGuard] },
  { path: 'admin/view-products', component: ViewProductsComponent },
  { path: 'admin/products', component: ProductsComponent },
  { path: 'admin/update-product/:id', component: UpdateProductComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  {
    path:'home', component: LayoutComponent,
    children: [
      { path:'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule) }
    ]
  },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], 
  exports: [RouterModule]
})
export class AppRoutingModule { }
