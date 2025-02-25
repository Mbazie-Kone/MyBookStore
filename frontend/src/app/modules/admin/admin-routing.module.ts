import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ProductsComponent } from './pages/products/products.component';
import { authGuard } from '../../guards/auth.guard';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';

const routes: Routes = [
  { path: 'admin/login', component: LoginComponent},
  { path: 'admin/regist', component: RegisterComponent},
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
