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
import { AdminLayoutComponent } from './core/components/admin/admin-layout/admin-layout.component';

const routes: Routes = [
  {
    path: 'admin/',
    component: AdminLayoutComponent,
    children: [
      { path: '', redirectTo: 'admin', pathMatch: 'full'},
      { path: 'admin', loadChildren: () => import('./modules/admin/admin.module').then(m => m.AdminModule) }
    ]
  },
  {
    path: '', 
    component: LayoutComponent,
    children: [
      { path: '', redirectTo: 'catalog', pathMatch: 'full' },
      { path: 'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule) }
    ]
  },
  { path: '**', component: ErrorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], 
  exports: [RouterModule]
})
export class AppRoutingModule { }
