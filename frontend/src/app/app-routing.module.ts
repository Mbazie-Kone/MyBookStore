import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DashboardComponent } from './admin/dashboard/dashboard.component';
import { authGuard } from './guards/auth.guard';
import { RegisterComponent } from './auth/register/register.component';
import { LayoutComponent } from './core/components/layout/layout.component';

const routes: Routes = [
  {
    path:'',
    component: LayoutComponent,
    children: [
      {path:'', loadChildren: () => import('./modules/home/home.module').then(m => m.HomeModule)},
      {path:'catalog', loadChildren: () => import('./modules/catalog/catalog.module').then(m => m.CatalogModule)},
      {path:'cart', loadChildren: () => import('./modules/cart/cart.module').then(m => m.CartModule)},
      {path:'account', loadChildren: () => import('./modules/account/account.module').then(m => m.AccountModule)}
    ]
  },
  {path: 'admin/login', component: LoginComponent},
  {path: 'admin/register', component: RegisterComponent},
  {path: 'admin/dashboard', component: DashboardComponent, canActivate: [authGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
